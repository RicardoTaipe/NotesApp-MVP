const express = require("express");
const app = express();

//Settings
//proces.env.port is a suggested when the app is deployed to cloud

app.set("port", process.env.PORT || 3000);

//Middleware
//Is not required to install body-parser in the current node version
app.use(express.json());

//Routes
app.use("/note", require("./routes/notes"));

app.get("*", (req, res) => {
  res.redirect("/note");
});
//Starting the server for requests
app.listen(app.get("port"), () => {
  console.log("Server on port", app.get("port"));
});
