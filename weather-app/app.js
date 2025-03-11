const canvas = document.getElementById('weatherCanvas');
const ctx = canvas.getContext('2d');
const temperatureElement = document.getElementById('temperature');
const locationElement = document.getElementById('location');
const conditionElement = document.getElementById('condition');

// Canvas setup
function resizeCanvas() {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
}
window.addEventListener('resize', resizeCanvas);
resizeCanvas();

// Weather particles system
const particles = [];
class Particle {
    constructor(x, y, type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.speed = Math.random() * 2 + 1;
        this.size = type === 'rain' ? 5 : type === 'snow' ? 4 : 0;
        this.angle = Math.random() * Math.PI * 2;
    }

    update() {
        switch(this.type) {
            case 'rain':
                this.y += this.speed * 8;
                if(this.y > canvas.height) this.y = -10;
                break;
            case 'snow':
                this.x += Math.cos(this.angle) * 0.5;
                this.y += this.speed;
                if(this.y > canvas.height) this.y = -10;
                break;
            case 'cloud':
                this.x += Math.cos(this.angle) * 0.2;
                this.y += Math.sin(this.angle) * 0.1;
                this.angle += 0.02;
                break;
        }
    }

    draw() {
        ctx.beginPath();
        switch(this.type) {
            case 'rain':
                ctx.strokeStyle = 'rgba(174, 194, 224, 0.8)';
                ctx.lineWidth = 2;
                ctx.moveTo(this.x, this.y);
                ctx.lineTo(this.x, this.y + this.size);
                ctx.stroke();
                break;
            case 'snow':
                ctx.fillStyle = 'rgba(255, 255, 255, 0.8)';
                ctx.arc(this.x, this.y, this.size, 0, Math.PI * 2);
                ctx.fill();
                break;
            case 'cloud':
                ctx.fillStyle = 'rgba(255, 255, 255, 0.1)';
                ctx.arc(this.x, this.y, 20, 0, Math.PI * 2);
                ctx.arc(this.x + 15, this.y - 10, 15, 0, Math.PI * 2);
                ctx.arc(this.x - 15, this.y - 10, 15, 0, Math.PI * 2);
                ctx.fill();
                break;
        }
    }
}

function animateWeather(condition) {
    particles.length = 0; // Clear existing particles
    
    // Create particles based on weather condition
    const particleCount = condition === 'rain' ? 300 : 
                         condition === 'snow' ? 150 :
                         condition === 'cloud' ? 20 : 0;

    for(let i = 0; i < particleCount; i++) {
        particles.push(new Particle(
            Math.random() * canvas.width,
            Math.random() * canvas.height,
            condition
        ));
    }

    function animate() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        
        // Draw background gradient based on condition
        const gradient = ctx.createLinearGradient(0, 0, 0, canvas.height);
        switch(condition) {
            case 'sun':
                gradient.addColorStop(0, '#87CEEB');
                gradient.addColorStop(1, '#4682B4');
                break;
            case 'rain':
                gradient.addColorStop(0, '#4B79A1');
                gradient.addColorStop(1, '#283E51');
                break;
            case 'snow':
                gradient.addColorStop(0, '#B0C4DE');
                gradient.addColorStop(1, '#6C7A89');
                break;
            default:
                gradient.addColorStop(0, '#1a1a2e');
                gradient.addColorStop(1, '#16213e');
        }
        ctx.fillStyle = gradient;
        ctx.fillRect(0, 0, canvas.width, canvas.height);

        particles.forEach(particle => {
            particle.update();
            particle.draw();
        });
        requestAnimationFrame(animate);
    }
    animate();
}

// Get weather data
async function getWeather(lat, lon) {
    try {
        const response = await fetch(`https://api.open-meteo.com/v1/forecast?latitude=${lat}&longitude=${lon}&hourly=temperature_2m,weathercode`);
        const data = await response.json();
        
        const currentHour = new Date().getHours();
        const temperature = data.hourly.temperature_2m[currentHour];
        const weatherCode = data.hourly.weathercode[currentHour];

        // Map weather code to condition
        let condition;
        if(weatherCode <= 1) condition = 'sun';
        else if(weatherCode >= 51 && weatherCode < 70) condition = 'rain';
        else if(weatherCode >= 70 && weatherCode < 90) condition = 'snow';
        else condition = 'cloud';

        // Update display
        temperatureElement.textContent = `${temperature}°C`;
        conditionElement.textContent = condition.charAt(0).toUpperCase() + condition.slice(1);
        animateWeather(condition);
    } catch (error) {
        console.error('Error fetching weather:', error);
        conditionElement.textContent = 'Unable to get weather data';
    }
}

// Location handling
const cityInput = document.getElementById('cityInput');
const searchBtn = document.getElementById('searchBtn');
const manualLocationDiv = document.querySelector('.manual-location');

// Get coordinates and location name by city using Open-Meteo geocoding
async function getCoordinates(city) {
    try {
        const response = await fetch(`https://geocoding-api.open-meteo.com/v1/search?name=${city}&count=1`);
        const data = await response.json();
        if(data.results && data.results.length > 0) {
            return {
                lat: data.results[0].latitude,
                lon: data.results[0].longitude,
                name: data.results[0].name
            };
        }
        return null;
    } catch (error) {
        console.error('Geocoding error:', error);
        return null;
    }
}

// Handle manual location search
searchBtn.addEventListener('click', async () => {
    if(cityInput.value.trim()) {
        const coords = await getCoordinates(cityInput.value.trim());
        if(coords) {
            getWeather(coords.lat, coords.lon);
            locationElement.textContent = coords.name;
        } else {
            conditionElement.textContent = 'City not found';
        }
    }
});

// Reverse geocoding to get city name from coordinates
async function getCityName(lat, lon) {
    try {
        const response = await fetch(`https://geocoding-api.open-meteo.com/v1/reverse?latitude=${lat}&longitude=${lon}`);
        const data = await response.json();
        return data.name || `${lat.toFixed(2)}°N, ${lon.toFixed(2)}°E`;
    } catch (error) {
        console.error('Reverse geocoding error:', error);
        return `${lat.toFixed(2)}°N, ${lon.toFixed(2)}°E`;
    }
}

// Attempt geolocation first
if(navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(async position => {
        const { latitude, longitude } = position.coords;
        const cityName = await getCityName(latitude, longitude);
        getWeather(latitude, longitude);
        locationElement.textContent = cityName;
    }, error => {
        console.error('Geolocation error:', error);
        locationElement.textContent = 'Location access denied';
        manualLocationDiv.style.display = 'block';
    });
} else {
    locationElement.textContent = 'Geolocation not supported';
    manualLocationDiv.style.display = 'block';
}
