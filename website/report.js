
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

userJson = sortJSON( checkinJson,'checkInDate', '321');


for(var i = 0; i < userJson.length; i++ ) {
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


function fetchAPIs() {
fetch('http://ec2-18-223-239-89.us-east-2.compute.amazonaws.com:8080/checkin/all-users')
  .then(function(response) {
    return response.json();
  })
  .then(function(myJson) {
    console.log(JSON.stringify(myJson));
  	populateUserCharts(myJson)
  });

  fetch('http://ec2-18-223-239-89.us-east-2.compute.amazonaws.com:8080/checkin/all-checkins')
  .then(function(response) {
    return response.json();
  })
  .then(function(myJson) {
    console.log(JSON.stringify(myJson));
  	populateCheckinCharts(myJson)
  });
}

function buildBar() {
	var trace1 = {
  x: ['November', 'December', 'January', 'February', 'March', 'April'],
  y: [8.0, 8.0, 12.0, 12.0, 13.0, 50.0],
  type: 'bar',
  text: ['4.17 below the mean', '4.17 below the mean', '0.17 below the mean', '0.17 below the mean', '0.83 above the mean', '7.83 above the mean'],
  marker: {
    color: '#00adb5'
  }
};

var data = [trace1];

var layout = {
  title: 'User Count Last 6 Months',
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

function buildLine() {
	var trace1 = {
  x: [1, 2, 3, 4],
  y: [10, 15, 13, 17],
  type: 'scatter',
  color: '#00adb5',
  name: 'Checkins'
};

var trace2 = {
  x: [1, 2, 3, 4],
  y: [16, 5, 11, 9],
  type: 'scatter',
  color: '#222831',
  name: 'Users'
};

var data = [trace1, trace2];

var layout = { title: 'Users vs. Checkins Over Time' }


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
buildLine();
//buildPie();


