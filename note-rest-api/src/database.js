const mysql = require('mysql');

//setting config params

const mysqlConnection= mysql.createConnection({
	host: 'remotemysql.com',
	user: 'YOUR_USER_HERE',
	password: 'YOUR_PASSWORD_HERE',
	database: 'notesdb',
	multipleStatements: true
})

mysqlConnection.connect((err)=>{
	if(err){
		console.log(err)
		return;
	}else{
		console.log('Db is connected');
	}
});

module.exports = mysqlConnection;