from flask import Flask, request, render_template, send_file
import sqlite3
import os
from io import BytesIO

app = Flask(__name__)

# Create database and table if not exists
def init_db():
    with sqlite3.connect("media.db") as conn:
        cursor = conn.cursor()
        cursor.execute('''CREATE TABLE IF NOT EXISTS media (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            filename TEXT,
                            filedata BLOB,
                            filetype TEXT)''')
        conn.commit()

init_db()

@app.route('/')
def index():
    with sqlite3.connect("media.db") as conn:
        cursor = conn.cursor()
        cursor.execute("SELECT id, filename, filetype FROM media")
        files = cursor.fetchall()
    return render_template("index.html", files=files)

@app.route('/upload', methods=['POST'])
def upload():
    if 'file' not in request.files:
        return "No file uploaded", 400
    file = request.files['file']
    if file.filename == '':
        return "No selected file", 400
    
    file_data = file.read()
    file_type = file.content_type
    
    with sqlite3.connect("media.db") as conn:
        cursor = conn.cursor()
        cursor.execute("INSERT INTO media (filename, filedata, filetype) VALUES (?, ?, ?)",
                       (file.filename, file_data, file_type))
        conn.commit()
    return "File uploaded successfully! <a href='/'>Go back</a>"

@app.route('/media/<int:file_id>')
def get_media(file_id):
    with sqlite3.connect("media.db") as conn:
        cursor = conn.cursor()
        cursor.execute("SELECT filedata, filetype FROM media WHERE id = ?", (file_id,))
        file = cursor.fetchone()
        if file:
            return send_file(BytesIO(file[0]), mimetype=file[1])
    return "File not found", 404

if __name__ == '__main__':
    app.run(debug=True)
