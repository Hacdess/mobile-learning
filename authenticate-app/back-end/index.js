import sqlite3 from "sqlite3";

const db = new sqlite3.Database("./mydb.db");

const execute = async (db, sql) => {
  return new Promise((resolve , reject) => {
    db.exec(sql, (err) => {
      if (err) reject(err);
      else resolve();
    });
  });
};


try {
  await execute(
    db,
    `CREATE TABLE IF NOT EXISTS users (
      id INTEGER PRIMARY KEY,
      name TEXT NOT NULL,
      email TEXT NOT NULL UNIQUE)`
  );
} catch (err) {
  console.log(err)
} finally {
  db.close();
}