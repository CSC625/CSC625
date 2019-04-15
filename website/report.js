function sortJSON(data, key, way) {
    return data.sort(function(a, b) {
        var x = a[key]; var y = b[key];
        if (way === '123' ) { return ((x < y) ? -1 : ((x > y) ? 1 : 0)); }
        if (way === '321') { return ((x > y) ? -1 : ((x < y) ? 1 : 0)); }
    });
    return json;
}

function populateUserCharts(userJson) {

let firstnames = []
let lastnames = [];
let userID = [];
let uid = [];
let email = [];
let active = [];
let lastLogin = [];
let invalidAttempts = [];

userJson = sortJSON( userJson,'lastLogin', '321');


for(var i = 0; i < userJson.length; i++ ) {
	    firstnames.push(userJson[i].firstName);
	    lastnames.push(userJson[i].lastName);
	    userID.push(userJson[i].userID);
	    uid.push(userJson[i].uid);
	    email.push(userJson[i].email); 
	    active.push(userJson[i].active);
	    lastLogin.push(userJson[i].lastLogin);
	    invalidAttempts.push(userJson[i].invalidAttempts); 
	}

	buildUserTable(firstnames,lastnames,active,email,lastLogin);
}

function populateCheckinCharts(checkinJson) {

let checkInID = []
let student = [];
let studentID = [];
let studentFirstName = [];
let studentLastName = [];
let active = [];
let user = [];
let checkInDate = [];

checkinJson = sortJSON( checkinJson,'checkInDate', '321');


for(var i = 0; i < checkinJson.length; i++ ) {
	    checkInID.push(checkinJson[i].checkInID);
	    student.push(checkinJson[i].student);
	    studentID.push(checkinJson[i].student.studentID);
	    studentFirstName.push(checkinJson[i].student.studentFirstName);
	    studentLastName.push(checkinJson[i].student.studentLastName); 
	    active.push(checkinJson[i].active);
	    checkInDate.push(checkinJson[i].checkInDate);
	    user.push(checkinJson[i].user); 
	}

	buildCheckinTable(checkInID,studentFirstName,studentLastName,studentID,checkInDate);
}


var userJson ='not set';
var checkinJson ='not set';

function fetchAPIs() {
fetch('http://ec2-18-223-239-89.us-east-2.compute.amazonaws.com:8080/checkin/all-users')
  .then(function(response) {
    return response.json();
  })
  .then(function(myJson) {
    populateUserCharts(myJson)
    assignUserJson(myJson)
    buildBar(userJson)
  }).then(function() {
  fetch('http://ec2-18-223-239-89.us-east-2.compute.amazonaws.com:8080/checkin/all-checkins')
  .then(function(response) {
    return response.json();
  })
  .then(function(myJson) {
    populateCheckinCharts(myJson)
    assignCheckinJson(myJson)
  }).then(function() {
    buildLine(userJson, checkinJson);
  });
})
}

// Necessary to handle the asyc of fetch, probably not the best way
function assignUserJson(myJson) {
  userJson = myJson;
}
function assignCheckinJson(myJson) {
  checkinJson = myJson;
}


function buildBar(userJson) {

  let november = 0, december = 0, january = 0, february = 0, march = 0, april = 0;
  for(i = 0; i < userJson.length; i++) {

    var jsonDate = new Date(userJson[i].lastLogin)
    var month = jsonDate.getMonth() + 1;
    var year = jsonDate.getFullYear();
    if (month == 11 && year == 2018)
    {
      november++
    }
    if (month == 12 && year == 2018)
    {
      december++
    }
    if (month == 01 && year == 2019)
    {
      january++
    }
    if (month == 02 && year == 2019)
    {
      february++
    }
    if (month == 03 && year == 2019)
    {
      march++
    }
    if (month == 04 && year == 2019)
    {
      april++
    }
    
  }

	var trace1 = {
  x: ['November', 'December', 'January', 'February', 'March', 'April'],
  y: [november, december, january, february, march, april],
  type: 'bar',
  text: [],
  marker: {
    color: '#00adb5'
  }
};

var data = [trace1];

var layout = {
  title: 'Last Login Count - Last 6 Months',
  font:{
    family: 'Raleway, sans-serif'
  },
  showlegend: false,
  xaxis: {
    tickangle: -45
  },
  yaxis: {
    zeroline: false,
    gridwidth: 2
  },
  bargap :0.05
};

Plotly.newPlot('barDiv', data, layout);
}

function buildLine(userJson, checkinJson) {

  let november = 0, december = 0, january = 0, february = 0, march = 0, april = 0;
  for(i = 0; i < checkinJson.length; i++) {

    var jsonDate = new Date(checkinJson[i].checkInDate)
    var month = jsonDate.getMonth() + 1;
    var year = jsonDate.getFullYear();
    if (month == 11 && year == 2018)
    {
      november++
    }
    if (month == 12 && year == 2018)
    {
      december++
    }
    if (month == 01 && year == 2019)
    {
      january++
    }
    if (month == 02 && year == 2019)
    {
      february++
    }
    if (month == 03 && year == 2019)
    {
      march++
    }
    if (month == 04 && year == 2019)
    {
      april++
    }
    
  }


	var trace1 = {
  x: ['November', 'December', 'January', 'February', 'March', 'April'],
  y: [november, december, january, february, march, april],
  type: 'scatter',
  line: {
    color: '#23272B',
    width: 3.0
  },
  name: 'Checkins'
};



november = 0, december = 0, january = 0, february = 0, march = 0, april = 0;
  for(i = 0; i < userJson.length; i++) {

    var jsonDate = new Date(userJson[i].lastLogin)
    var month = jsonDate.getMonth() + 1;
    var year = jsonDate.getFullYear();
    if (month == 11 && year == 2018)
    {
      november++
    }
    if (month == 12 && year == 2018)
    {
      december++
    }
    if (month == 01 && year == 2019)
    {
      january++
    }
    if (month == 02 && year == 2019)
    {
      february++
    }
    if (month == 03 && year == 2019)
    {
      march++
    }
    if (month == 04 && year == 2019)
    {
      april++
    }
    
  }
var trace2 = {
  x: ['November', 'December', 'January', 'February', 'March', 'April'],
  y: [november, december, january, february, march, april],
  type: 'scatter',
  line: {
    color: '#00adb5',
    width: 3.0
  },
  name: 'Users'
};

var data = [trace1, trace2];

var layout = { title: 'Users vs. Checkins - Last 6 Months' }


Plotly.newPlot('lineDiv', data, layout, {responsive: true});
}

function buildPie() {
	var data = [{
  values: [16, 15, 12, 6, 5, 4, 42],
  labels: ['US', 'China', 'European Union', 'Russian Federation', 'Brazil', 'India', 'Rest of World' ],
  domain: {column: 0},
  name: 'GHG Emissions',
  hoverinfo: 'label+percent+name',
  hole: .4,
  type: 'pie'
},{
  values: [27, 11, 25, 8, 1, 3, 25],
  labels: ['US', 'China', 'European Union', 'Russian Federation', 'Brazil', 'India', 'Rest of World' ],
  text: 'CO2',
  textposition: 'inside',
  domain: {column: 1},
  name: 'CO2 Emissions',
  hoverinfo: 'label+percent+name',
  hole: .4,
  type: 'pie'
}];

var layout = {
  title: 'Something',
  annotations: [
    {
      font: {
        size: 20
      },
      showarrow: false,
      text: 'GHG',
      x: 0.17,
      y: 0.5
    },
    {
      font: {
        size: 20
      },
      showarrow: false,
      text: 'CO2',
      x: 0.82,
      y: 0.5
    }
  ],
  height: 400,
  width: 600,
  showlegend: false,
  grid: {rows: 1, columns: 2}
};

Plotly.newPlot('pieDiv', data, layout, {responsive: true});
}

function buildUserTable(firstnames,lastnames,active,email,lastLogin) {

	var values = [
      firstnames,
      lastnames,
      active,
      email,
      lastLogin]

var data = [{
  type: 'table',
  header: {
    values: [["First name"], ["Last name"],
				 ["Active"], ["Email"], ["Last Login"]],
    align: "center",
    line: {width: 1, color: 'black'},
    fill: {color: "#00adb5"},
    font: {family: "Arial", size: 12, color: "white"}
  },
  cells: {
    values: values,
    align: "center",
    line: {color: "black", width: 1},
    font: {family: "Arial", size: 11, color: ["black"]}
  }
}]

var layout = {
	title: 'Users'
}

Plotly.plot('tableDiv', data, layout, {responsive: true});
}

function buildCheckinTable(checkInID,studentFirstName,studentLastName,studentID,checkInDate) {

	for (i = 0; i < checkInDate.length; i++) {
		d = new Date(checkInDate[i]);
		checkInDate[i] = d.getFullYear()  + "-" + (d.getMonth()+1) + "-" + d.getDate();

}

	var values = [
      checkInID,
      studentFirstName,
      studentLastName,
      studentID,
      checkInDate]

var data = [{
  type: 'table',
  header: {
    values: [["Checkin ID"], ["Student First Name"],
				 ["Student Last Name"], ["Student ID"], ["Checkin Date"]],
    align: "center",
    line: {width: 1, color: 'black'},
    fill: {color: "#00adb5"},
    font: {family: "Arial", size: 12, color: "white"}
  },
  cells: {
    values: values,
    align: "center",
    line: {color: "black", width: 1},
    font: {family: "Arial", size: 11, color: ["black"]}
  }
}]

var layout = {
	title: 'Checkins'
}

Plotly.plot('tableDiv2', data, layout, {responsive: true});
}

fetchAPIs();

buildBar();
//buildPie();


