const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const mysql = require('mysql');
 
// parse application/json
app.use(bodyParser.json());
 
//create database connection
const conn = mysql.createConnection({
port:"3301",
  host: 'localhost',
  user: 'root',
  password: 'admin',
  database: 'sensor_project'
});
 
//connect to database
conn.connect((err) =>{
  if(err) throw err;
  console.log('Mysql Connected...');
});
 
//show all products
app.get('/api/meters',(req, res) => {
  let sql = "SELECT * FROM meters";
  let query = conn.query(sql, (err, results) => {
    if(err) throw err;
    res.send(JSON.stringify(results));
  });
});
//show info from specific inverter
app.get('/api/inverters',(req, res) => {
  let sql = "SELECT * FROM inverterinfo";
  let query = conn.query(sql, (err, results) => {
    if(err) throw err;
    res.send(JSON.stringify(results));
  });
});

//show last month production inverter
app.get('/api/meters/inverterdata/month/:id',(req, res) => {
  let sql = "select MONTH(time) as month, SUM(pv_productie) from inverterdata where ean_nummer =" +req.params.id+ " and MONTH(time) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH) group by month";
  let query = conn.query(sql, (err, results) => {
    if(err) throw err;
    res.send(JSON.stringify(results));
  });
});

//show all data from specific meter
app.get('/api/meters/sensordata/:id',(req, res) => {
  let sql = "SELECT * FROM sensordata where ean_nummer ="+ req.params.id;
  let query = conn.query(sql, (err, results) => {
    if(err) throw err;
    res.send(JSON.stringify(results));
  });
});

//show all data from specific inverter
app.get('/api/meters/inverterdata/:id',(req, res) => {
  let sql = "SELECT * FROM inverterdata where ean_nummer ="+ req.params.id;
  let query = conn.query(sql, (err, results) => {
    if(err) throw err;
    res.send(JSON.stringify(results));
  });
});
// show specific data from specific meter
app.get('/api/meters/sensordata/:id/:startdd/:enddate',(req, res) => {
  let sql = "SELECT * FROM sensordata WHERE (cast(time as date) between '" + req.params.startdd + "' and '" + req.params.enddate + "' ) AND ean_nummer ="+ req.params.id;
  let query = conn.query(sql, (err, results) => {
    if(err) throw err;
    res.send(JSON.stringify(results));
  });
});

app.get('/api/meters/inverterdata/:id/:startdd/:enddate',(req, res) => {
  let sql = "SELECT * FROM inverterdata WHERE (cast(time as date) between '" + req.params.startdd + "' and '" + req.params.enddate + "' ) AND ean_nummer ="+ req.params.id;
  let query = conn.query(sql, (err, results) => {
    if(err) throw err;
    res.send(JSON.stringify(results));
  });
});


 
//Server listening
app.listen(3001,() =>{
  console.log('Server started on port 3001...');
});