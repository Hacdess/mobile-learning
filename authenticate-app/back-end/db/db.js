import sqlite3 from "sqlite3";
import path from 'path';
import { fileURLToPath } from 'url';
import fs from 'fs';

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const dbPath = path.resolve(__dirname, 'create_table.db');

const db = new (sqlite3.verbose().Database)(
  dbPath,
  (err) => {
    if (err) {
      console.log("Error opening database: " + err.message);
    } else {
      console.log("Connected to the SQLite database.");
    }
  }
);

export const execute = (sql, params = []) => {
  return new Promise((resolve, reject) => {
    db.run(sql, params, function (err) {
      if (err) reject(err);
      else resolve({id: this.lastID, changes: this.changes});
    })
  });
}

export const fetch = (sql, params = []) => {
  return new Promise((resolve, reject) => {
    db.get(sql, params, (err, row) => {
      if (err) reject(err);
      else resolve(row);
    })
  })
}

export const fetch_all = (sql, params = []) => {
  return new Promise((resolve, reject) => {
    db.all(sql, params, (err, rows) => {
      if (err) reject(err);
      else resolve(rows);
    })
  })
}

const initDatabase = () => {
  try {
    const sqlFilePath = path.resolve(__dirname, 'create_table.sql');
    const sqlSchema = fs.readFileSync(sqlFilePath, 'utf8'); // Đọc nội dung file SQL
    
    db.exec(sqlSchema, (err) => {
      if (err) console.log("❌ Lỗi tạo bảng: ", err.message);
      else console.log("🚀 Đã khởi tạo cấu trúc bảng từ create_table.sql thành công!");
    });
  } catch (error) {
    console.log("❌ Không tìm thấy hoặc không thể đọc file create_table.sql", error.message);
  }
};

initDatabase();

export default db;