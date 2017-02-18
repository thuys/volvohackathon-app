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
	
	//this.showLogin();
	controller.showFleet();
	controller.createHeatMap();
	
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
		url: "/VolvoHackathon-App/java/fleet/1",
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
	
	console.log(position);
	
	var controller = this;
	var alertPanel = sap.ui.getCore().byId("alertPanel");
	alertPanel.destroyContent();
	
	controller.alertMap = new google.maps.Map(document.getElementById('alertMap'), {
		center: {lat: 51.21728, lng: 4.41728},
		zoom: 6
	});
	
	jQuery.ajax({
		url: "/VolvoHackathon-App/java/alert/1",
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
						text: "Probability of Damage: " + truck.information.propabilityOfDamage + "%"
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
					}).addStyleClass("dashboardTileMargin").attachEvent("click", function () {
						console.log("click");
					}),
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
	
},

createHeatMap: function () {
	
	var controller = this;
	
//	jQuery.ajax({
//		url: "/VolvoHackathon-App/java/fleet/1",
//		method: 'GET', 
//		dataType: 'json',
//		success: function (response) {
//			
//			controller.heatmap = new google.maps.Map(document.getElementById('reportMap'), {
//				center: {lat: 51.21728, lng: 4.41728},
//				zoom: 8
//			});
//			
//			var trucks = response.trucks;
//			
//			for(var i = 0; i < trucks.length; i++) {
//				
//				var circle = new google.maps.Circle({
//				      strokeColor: colour,
//				      strokeOpacity: 0.8,
//				      strokeWeight: 2,
//				      fillColor: colour,
//				      fillOpacity: 0.35,
//				      map: controller.alertMap,
//				      center: checkpoint.position,
//				      radius: 20000
//				});
//				
//			}
//			
//		},
//		error: function (error) {
//			console.log(error);
//		}
//	});
	
}

});