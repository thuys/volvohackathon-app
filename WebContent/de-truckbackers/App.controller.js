sap.ui.controller("de-truckbackers.App", {
	
	overviewMap: null,
	alertMap: null,
	heatmap: null,


/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf de-truckbackers.App
*/
//	onInit: function() {
//
//	},

/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf de-truckbackers.App
*/
onBeforeRendering: function() {
	
},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf de-truckbackers.App
*/
onAfterRendering: function() {
	
	var controller = this;
	
	this.showLogin();
	controller.showFleet();
	
	controller.overviewMap = new google.maps.Map(document.getElementById('map'), {
		center: {lat: 51.21728, lng: 4.41728},
		zoom: 8
	});
	
},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf de-truckbackers.App
*/
//	onExit: function() {
//
//	}

//#LOGIN
showLogin: function () {
	
	var shellContent = document.getElementsByClassName("sapMShellCentralBox");
	shellContent[0].style.opacity = "0"; 
	
	var userNameText = new sap.m.Text({
		text: "Username",
		width: "150px"
	}).addStyleClass("marginTop15");
	
	var userNameField = new sap.m.Input({
		width: "200px",
	});

	var userNameBox = new sap.m.HBox({
		items: [
			userNameText,
			userNameField
		]
	});
	
	var passwordText = new sap.m.Text({
		text: "Password",
		width: "150px"
	}).addStyleClass("marginTop15");
	
	var passwordField = new sap.m.Input({
		width: "200px",
		type: sap.m.InputType.Password
	});

	var passwordBox = new sap.m.HBox({
		items: [
			passwordText,
			passwordField
		]
	});
	
	var loginVBox = new sap.m.VBox({
		items: [
			userNameBox,
			passwordBox
		]
	});
	
	var dialogue = new sap.m.Dialog({
		title: "Login required",
		type: "Message",
		contentWidth: "450px",
		content: [
			loginVBox
		],
		endButton: new sap.m.Button({
			width: "98%",
			text: "Login",
			press: function () {
				
				dialogue.close();
				shellContent[0].style.opacity = "1";
			}
		}),
		afterClose: function () {
			dialogue.destroy();
		}
	});
	
	dialogue.open();
	
},

//#FLEETOVERVIEW
createDashboard: function (data) {
	
	var firstRow = new sap.m.HBox({
		items: [
			new sap.m.Panel({
				id: "checkPointRange",
				width: "280px",
				height: "180px",
				content: [
					new sap.m.HBox({
						justifyContent: "Center",
						height: "100%",
						items: [
							new sap.m.VBox({
								justifyContent: "Center",
								items: [
									new sap.m.Text({
										text: data.numberInCheckPointRange,
									}).addStyleClass("dashboardTileValue"),
									new sap.m.Text({
										text: "Within Checkpoint Range",
										width: "240px"
									}).addStyleClass("dashboardTileText")
								]
							})
						]
					})
				]
			}).addStyleClass("dashboardTileMargin"),
			new sap.m.Panel({
				id: "speedingBreaches",
				width: "280px",
				height: "180px",
				content: [
					new sap.m.HBox({
						justifyContent: "Center",
						height: "100%",
						items: [
							new sap.m.VBox({
								justifyContent: "Center",
								items: [
									new sap.m.Text({
										text: data.numberOfSpeedingBreaches,
									}).addStyleClass("dashboardTileValue"),
									new sap.m.Text({
										text: "Speeding Breaches",
										width: "240px"
									}).addStyleClass("dashboardTileText")
								]
							})
						]
					})
				]
			}).addStyleClass("dashboardTileMargin"),
			new sap.m.Panel({
				id: "idling",
				width: "280px",
				height: "180px",
				content: [
					new sap.m.HBox({
						justifyContent: "Center",
						height: "100%",
						items: [
							new sap.m.VBox({
								justifyContent: "Center",
								items: [
									new sap.m.Text({
										text: data.numberOfHoursIdle,
									}).addStyleClass("dashboardTileValue"),
									new sap.m.Text({
										text: "Idling",
										width: "240px"
									}).addStyleClass("dashboardTileText")
								]
							})
						]
					})
				]
			}).addStyleClass("dashboardTileMargin"),
			new sap.m.Panel({
				id: "shocks",
				width: "280px",
				height: "180px",
				content: [
					new sap.m.HBox({
						justifyContent: "Center",
						height: "100%",
						items: [
							new sap.m.VBox({
								justifyContent: "Center",
								items: [
									new sap.m.Text({
										text: data.numberOfShocks,
									}).addStyleClass("dashboardTileValue"),
									new sap.m.Text({
										text: "Shocks",
										width: "240px"
									}).addStyleClass("dashboardTileText")
								]
							})
						]
					})
				]
			}).addStyleClass("dashboardTileMargin")
		]
	});
	
	var secondRow = new sap.m.HBox({
		items: [
			new sap.m.Panel({
				id: "attentionZones",
				width: "280px",
				height: "180px",
				content: [
					new sap.m.HBox({
						justifyContent: "Center",
						height: "100%",
						items: [
							new sap.m.VBox({
								justifyContent: "Center",
								items: [
									new sap.m.Text({
										text: data.numberInAttentionZone,
									}).addStyleClass("dashboardTileValue"),
									new sap.m.Text({
										text: "In Attention Zones",
										width: "240px"
									}).addStyleClass("dashboardTileText")
								]
							})
						]
					})
				]
			}).addStyleClass("dashboardTileMargin"),
			new sap.m.Panel({
				id: "lockBreaches",
				width: "280px",
				height: "180px",
				content: [
					new sap.m.HBox({
						justifyContent: "Center",
						height: "100%",
						items: [
							new sap.m.VBox({
								justifyContent: "Center",
								items: [
									new sap.m.Text({
										text: data.numberOfLockBreaches,
									}).addStyleClass("dashboardTileValue"),
									new sap.m.Text({
										text: "Lock Breaches",
										width: "240px"
									}).addStyleClass("dashboardTileText")
								]
							})
						]
					})
				]
			}).addStyleClass("dashboardTileMargin"),
			new sap.m.Panel({
				id: "UnusualAreas",
				width: "280px",
				height: "180px",
				content: [
					new sap.m.HBox({
						justifyContent: "Center",
						height: "100%",
						items: [
							new sap.m.VBox({
								justifyContent: "Center",
								items: [
									new sap.m.Text({
										text: data.numberIfUnusualAreas,
									}).addStyleClass("dashboardTileValue"),
									new sap.m.Text({
										text: "Unusual areas",
										width: "240px"
									}).addStyleClass("dashboardTileText")
								]
							})
						]
					})		
				]
			}).addStyleClass("dashboardTileMargin"),
			new sap.m.Panel({
				id: "harshBrakes",
				width: "280px",
				height: "180px",
				content: [
					new sap.m.HBox({
						justifyContent: "Center",
						height: "100%",
						items: [
							new sap.m.VBox({
								justifyContent: "Center",
								items: [
									new sap.m.Text({
										text: data.numberOfHarshBrakes,
									}).addStyleClass("dashboardTileValue"),
									new sap.m.Text({
										text: "Harsh Brakes",
										width: "240px"
									}).addStyleClass("dashboardTileText")
								]
							})
						]
					})
				]
			}).addStyleClass("dashboardTileMargin")
		]
	});
	
	var tileBox = new sap.m.VBox({
		items: [
			firstRow,
			secondRow
		]
	}).addStyleClass("tileStyleSingle");
	
	sap.ui.getCore().byId("dashboardPanel").addContent(tileBox);
	
},

//#MAP FUNCTIONS
positionGeofence: function () {
	
	var coordinates = [
	      {lat: 25.774, lng: -80.190},
	      {lat: 18.466, lng: -66.118},
	      {lat: 32.321, lng: -64.757},
	      {lat: 34.321, lng: -62.757}
	  ];
	
	
	var controller = this;
	
	var polygon = new google.maps.Polygon({
		paths: coordinates,
	    strokeColor: '#FF0000',
	    strokeOpacity: 0.8,
	    strokeWeight: 3,
	    fillColor: '#FF0000',
	    fillOpacity: 0.35
	  });
	
	polygon.setMap(this.overviewMap);
	
},

showFleet: function () {
	
	var controller = this;
	
	controller.overviewMap = new google.maps.Map(document.getElementById('map'), {
		center: {lat: 51.21728, lng: 4.41728},
		zoom: 8
	});
	
	jQuery.ajax({
		url: "/VolvoHackathon-App/java/fleet/"+Math.floor(Math.random() * 3) + 1  ,
		method: 'GET', 
		dataType: 'json',
		success: function (response) {
			
			controller.createDashboard(response);
			
			var trucks = response.trucks;
			
			for(var i = 0; i < trucks.length; i++) {
				
				marker = new google.maps.Marker({
					 position: new google.maps.LatLng(trucks[i].position.lat, trucks[i].position.lng),
				     map: controller.overviewMap,
				     icon: trucks[i].status == 'GREEN' ? 'resources/images/truck_icon_green.png' : (trucks[i].status == 'RED' ? 'resources/images/truck_icon_red.png':'resources/images/truck_icon_orange.png')
				 });
				 
				 google.maps.event.addListener(marker, 'click', (function(marker, i) {
					return function() {
						
						var position = {
								lat: trucks[i].position.lat,
								lng: trucks[i].position.lng
						};
						
					    sap.ui.getCore().byId("alertManagement").setEnabled(true);
					    var bar = sap.ui.getCore().byId("appTabBar").setSelectedKey("alertManagement");
					    controller.createTruckInformation(position);
					    
					}
				 })(marker, i));
				
			}
			
		},
		error: function (error) {
			console.log(error);
		}
	});
	
},

createTruckInformation: function (position) {
	
	var controller = this;
	var alertPanel = sap.ui.getCore().byId("alertPanel");
	alertPanel.destroyContent();
	
	controller.alertMap = new google.maps.Map(document.getElementById('alertMap'), {
		center: {lat: 51.21728, lng: 4.41728},
		zoom: 6
	});
	
	jQuery.ajax({
		url: "/VolvoHackathon-App/java/alert/" +Math.floor(Math.random() * 1000),
		method: 'GET', 
		dataType: 'json',
		success: function (response) {
	
			var truck = response;

			marker = new google.maps.Marker({
				 position: new google.maps.LatLng(position.lat, position.lng),
			     map: controller.alertMap,
			     icon: 'resources/images/truck_icon.png'
			 });
			
			var truckInfoArray = [];
			truckInfoArray.push({text: "Driver Score:", value: truck.information.driverScore, colourcode : truck.information.driverScoreColour});
			truckInfoArray.push({text: "Asset Score:", value: truck.information.assetScore, colourcode : truck.information.assetScoreColour});
			truckInfoArray.push({text: "Geo Score:", value: truck.information.geoScore, colourcode : truck.information.geoScoreColour});
			
			var alertHBox = new sap.m.HBox({});
			var alertStatsVBox = new sap.m.VBox({}).addStyleClass("tileStyleLeft");
			
			for (var i = 0; i < truckInfoArray.length; i++) {
				
				var sliderElement = new sap.m.VBox({
					items: [
						new sap.m.Text({
							text: truckInfoArray[i].text
						}).addStyleClass("dashboardTileText"),
						new sap.m.ProgressIndicator({
							width: "250px",
							percentValue: truckInfoArray[i].value,
							enabled: false,
							state:truckInfoArray[i].colourcode,
							displayValue:truckInfoArray[i].value + "%"
						})
					]
				}).addStyleClass("alertSlider");
				
				alertStatsVBox.addItem(sliderElement);
			}
			
			alertHBox.addItem(alertStatsVBox);
			
			var alertProbHBox = new sap.m.HBox({
				justifyContent: "Center",
				items: [
					new sap.m.Text({
						text: "Probability of Damage: " + truck.information.propabilityOfDamage*100 + "%"
					}).addStyleClass("alertActions")
				]
			});
			
			var alertIssueClickHBox = new sap.m.HBox({
				items: [
					new sap.m.Panel({
						id: "callDriverPanel",
						width: "275px",
						height: "220px",
						content: [
							new sap.m.HBox({
								justifyContent: "Center",
								height: "100%",
								items: [
									new sap.m.VBox({
										justifyContent: "Center",
										items: [
											new sap.m.Text({
												text: "Call Driver",
											}).addStyleClass("alertActions")
										]
									})
								]
							})
						]
					}).addStyleClass("dashboardTileMargin"),
					new sap.m.Panel({
						width: "275px",
						height: "220px",
						content: [
							new sap.m.HBox({
								justifyContent: "Center",
								height: "100%",
								items: [
									new sap.m.VBox({
										justifyContent: "Center",
										items: [
											new sap.m.Text({
												text: "Plan Inspection",
											}).addStyleClass("alertActions")
										]
									})
								]
							})
						]
					}).addStyleClass("dashboardTileMargin"),
					new sap.m.Panel({
						width: "275px",
						height: "220px",
						content: [
							new sap.m.HBox({
								justifyContent: "Center",
								height: "100%",
								items: [
									new sap.m.VBox({
										justifyContent: "Center",
										items: [
											new sap.m.Text({
												text: "Detailed Log",
											}).addStyleClass("alertActions")
										]
									})
								]
							})
						]
					}).addStyleClass("dashboardTileMargin")
				]
			});
			
			var alertIssueVBox = new sap.m.VBox({}).addStyleClass("tileStyleRight");
			alertIssueVBox.addItem(alertProbHBox);
			alertIssueVBox.addItem(alertIssueClickHBox);
			alertHBox.addItem(alertIssueVBox);
			
			alertPanel.addContent(alertHBox);
			
			controller.displayCheckpoints(truck);
			
		},
		error: function (error) {
			console.log(error);
		}
	});
},

displayCheckpoints: function (truck) {
	
	var controller = this;
	var checkpoints = truck.information.checkPoints;
	console.log(truck);
	console.log(checkpoints);
	
	var bool = false;

	for(var i= 0; i < checkpoints.length; i++) {
		
		var checkpoint = checkpoints[i];
		
		if (checkpoint.doneStatus == "done") {
			colour = "#0022FF";
		}
		else if (checkpoint.doneStatus == "current") {
			colour = "#08FA00";
		}
		else {
			colour = "#6B6E85";
		}

		var circle = new google.maps.Circle({
		      strokeColor: colour,
		      strokeOpacity: 0.8,
		      strokeWeight: 2,
		      fillColor: colour,
		      fillOpacity: 0.35,
		      map: controller.alertMap,
		      center: checkpoint.position,
		      radius: 20000
		});
		
	}
	
	controller.createRoute(checkpoints);
	
},

createRoute: function (checkpoints) {
	
	var controller = this;
	
	var start = checkpoints[0].position;
	var end = checkpoints[checkpoints.length-1].position;
	var routePoints = [];
		
	for(var i = 1; i < checkpoints.length-2; i++) {
		var p = {
			location:checkpoints[i].position,
			stopover:false
		};
		routePoints.push(p);
	}  
	
	var controller = this;
	var request = {
			origin: start,
			destination: end,
			waypoints: routePoints,
			travelMode: google.maps.TravelMode.DRIVING
	};
	
	var directionsDisplay = new google.maps.DirectionsRenderer();
	directionsDisplay.setMap(controller.alertMap);
	var directionsService = new google.maps.DirectionsService();
	
	directionsService.route(request, function(result, status) {
	    if (status == google.maps.DirectionsStatus.OK) {
	    	directionsDisplay.setDirections(result);
	    } else {
	      alert("couldn't get directions:" + status);
	    }
	});
	
},

createHeatMap: function () {
	
	var controller = this;
	var bar = sap.ui.getCore().byId("appTabBar");
	
	if (bar.getSelectedKey() == "reports") {
		
		jQuery.ajax({
			url: "/VolvoHackathon-App/java/heat ",
			method: 'GET', 
			dataType: 'json',
			success: function (response) {
				
				console.log(response);
				
				var heatpoints = response.heatPoints;
				
				if (controller.heatmap == null) {
					controller.heatmap = new google.maps.Map(document.getElementById('heatMap'), {
						center: {lat: 51.21728, lng: 4.41728},
						zoom: 8
					});
				}
				
				for(var i= 0; i < heatpoints.length; i++) {
					
					var heatpoint = heatpoints[i];
					
					console.log(heatpoint);

					var circle = new google.maps.Circle({
					      strokeColor: "#" + heatpoint.color,
					      strokeOpacity: 0.8,
					      strokeWeight: 2,
					      fillColor: "#" + heatpoint.color,
					      fillOpacity: 0.35,
					      map: controller.heatmap,
					      center: heatpoint.position,
					      radius: 20000
					});
					
				}
				
			},
			error: function (error) {
				console.log(error);
			}
		});	
	}
	
	controller.createReportTables();
},

createReportTables: function () {
	
	var controller = this;
	var alertReportPanel = sap.ui.getCore().byId("alertReportPanel");
	alertReportPanel.destroyContent();
	
	var alertJSONModel = new sap.ui.model.json.JSONModel();
	sap.ui.getCore().setModel(this.alertJSONModel, "alertJSONModel");
	
	var alertTable = new sap.m.Table({
		columns: [
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "Alert Area"})
			}),
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "Alert Outcome"})
			}),
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "Alert Type"})
			}),
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "Date"})
			}),
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "Time"})
			}),
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "Truck ID"})
			})
		]
	});
	
	var alertTableListItem = new sap.m.ColumnListItem({
		id: "alertTableListItem",
		type: "Active"
	});
	
	var listitem = new sap.m.Text({
		text: "{alertJSONModel>alertArea}"
	});
	alertTableListItem.addCell(listitem);
	
	var listitem = new sap.m.Text({
		text: "{alertJSONModel>alertOutcome}"
	});
	alertTableListItem.addCell(listitem);
	
	var listitem = new sap.m.Text({
		text: "{alertJSONModel>alertType}"
	});
	alertTableListItem.addCell(listitem);
	
	var listitem = new sap.m.Text({
		text: "{alertJSONModel>date}"
	});
	alertTableListItem.addCell(listitem);
	
	var listitem = new sap.m.Text({
		text: "{alertJSONModel>time}"
	});
	alertTableListItem.addCell(listitem);
	
	var listitem = new sap.m.Text({
		text: "{alertJSONModel>truckId}"
	});
	alertTableListItem.addCell(listitem);
	
	alertJSONModel.setData(alertData);
	alertTable.bindItems("alertJSONModel>/", alertTableListItem);
	alertTable.setModel(alertJSONModel, "alertJSONModel");	
	
	alertReportPanel.addContent(alertTable);
	
	//Driver table
	var driverReportPanel = sap.ui.getCore().byId("driverReportPanel");
	driverReportPanel.destroyContent();
	
	var driverJSONModel = new sap.ui.model.json.JSONModel();
	sap.ui.getCore().setModel(this.driverJSONModel, "driverJSONModel");
	
	var driverTable = new sap.m.Table({
		columns: [
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "#Fatigue breaches"})
			}),
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "#Speed breaches"})
			}),
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "Driver ID"})
			}),
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "Driving time"})
			}),
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "Idling time"})
			}),
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "KM driven (2017)"})
			}),
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "KM driven (CW)"})
			}),
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "Total breaches (2017)"})
			}),
			new sap.m.Column ({
				width: "100px",
				header: new sap.m.Label({text: "Total breaches (CW)"})
			})
			
		]
	});
	
	var driverTableListItem = new sap.m.ColumnListItem({
		id: "driverTableListItem",
		type: "Active"
	});
	
	var listitem = new sap.m.Text({
		text: "{driverJSONModel>Fatiguebreaches}"
	});
	driverTableListItem.addCell(listitem);
	
	var listitem = new sap.m.Text({
		text: "{driverJSONModel>Speedbreaches}"
	});
	driverTableListItem.addCell(listitem);
	
	var listitem = new sap.m.Text({
		text: "{driverJSONModel>DriverID}"
	});
	driverTableListItem.addCell(listitem);
	
	var listitem = new sap.m.Text({
		text: "{driverJSONModel>Drivingtime}"
	});
	driverTableListItem.addCell(listitem);
	
	var listitem = new sap.m.Text({
		text: "{driverJSONModel>Idlingtime}"
	});
	driverTableListItem.addCell(listitem);
	
	var listitem = new sap.m.Text({
		text: "{driverJSONModel>KMdrivenYear}"
	});
	driverTableListItem.addCell(listitem);
	
	var listitem = new sap.m.Text({
		text: "{driverJSONModel>KMdrivenWeek}"
	});
	driverTableListItem.addCell(listitem);
	
	var listitem = new sap.m.Text({
		text: "{driverJSONModel>TotalbreachesYear}"
	});
	driverTableListItem.addCell(listitem);
	
	var listitem = new sap.m.Text({
		text: "{driverJSONModel>TotalbreachesWeek}"
	});
	driverTableListItem.addCell(listitem);	
	
	driverJSONModel.setData(driverData);
	driverTable.bindItems("driverJSONModel>/", driverTableListItem);
	driverTable.setModel(driverJSONModel, "driverJSONModel");	
	
	driverReportPanel.addContent(driverTable);
	
}

});