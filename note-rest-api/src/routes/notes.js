const express = require("express");

//Create an object to define Routes in server
const router = express.Router();

const mysqlConnection = require("../database");

//Retrieve all notes from mysql db
router.get("/", (req, res) => {
  mysqlConnection.query("SELECT * FROM notes;", (err, rows, fields) => {
    if (!err) {
      res.json(rows);
    } else {
      console.log(err);
    }
  });
});

//Retrieve an specific note
router.get("/:id", (req, res) => {
  const { id } = req.params;
  //avoid sql injection
  mysqlConnection.query(
    "SELECT * FROM notes WHERE id = ?;",
    [id],
    (error, rows, fields) => {
      if (!error) {
        res.json(rows[0]);
      } else {
        console.log(error);
      }
    }
  );
});

//Create a new note
router.post("/", (req, res) => {
  const { title, note } = req.body;
  const query = `INSERT INTO notes (title,note) VALUES (?,?);`;
  mysqlConnection.query(query, [title, note], (err, rows, fields) => {
    if (!err) {
      res.json({ success: true, message: "Note Saved" });
    } else {
      res.json({ success: false, message: "Error" });
      console.log(err);
    }
  });
});

//Update an note
router.put("/", (req, res) => {
  //const { id } = req.params;
  const { id, title, note } = req.body;
  const query = `UPDATE notes SET title=?, note=? WHERE id=?;`;
  mysqlConnection.query(query, [title, note, id], (err, rows, fields) => {
    if (!err) {
      res.json({ success: true, message: "Note Updated" });
    } else {
      res.json({ success: false, message: "Error" });
    }
  });
});

//Delete note by id
router.delete("/:id", (req, res) => {
  const { id } = req.params;
  console.log(req.body);
  mysqlConnection.query(
    "DELETE FROM notes WHERE id=?",
    [id],
    (err, rows, fields) => {
      if (!err) {
        res.json({ success: true, message: "Note deleted" });
      } else {
        res.json({ success: false, message: "Error" });
      }
    }
  );
});

module.exports = router;
