sap.ui.jsview("de-truckbackers.App", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf de-truckbackers.App
	*/ 
	getControllerName : function() {
		return "de-truckbackers.App";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf de-truckbackers.App
	*/ 
	
	createContent : function(oController) {
 		return new sap.m.Page({
 			showHeader: false,
			content: [
				new sap.m.VBox({
					items: [
						new sap.m.HBox({
							justifyContent: "Center",
							width: "100%",
							items: [
								new sap.ui.core.HTML({
									content: '<div id="header"></div>'
								})
							]
						}),
						new sap.m.IconTabBar({
							id: "appTabBar",
							items: [
								new sap.m.IconTabFilter({
									text: "Fleet Overview",
									key: "fleetOverview",
									content: [
										new sap.m.VBox({
											width: "100%",
											items: [
												new sap.m.Panel({
													width: "100%",
													height: "600px",
													content: [
														new sap.ui.core.HTML({
															content: '<div id="map"></div>'
														})
													]
												}),
												new sap.m.Panel({
													id: "dashboardPanel"
												})
											]
										})
									]
								}),
								new sap.m.IconTabFilter({
									text: "Alert Management",
									key: "alertManagement",
									id: "alertManagement",
									enabled: false,
									content: [
										new sap.m.VBox({
											width: "100%",
											items: [
												new sap.m.Panel({
													width: "100%",
													height: "400px",
													content: [
														new sap.ui.core.HTML({
															content: '<div id="alertMap"></div>'
														})
													]
												}),
												new sap.m.Panel({
													id: "alertPanel",
												})
											]
										})
									]
								}),
								new sap.m.IconTabFilter({
									text: "Reports",
									content: [
										new sap.m.VBox({
											width:"100%",
											items:[
												new sap.m.Panel({
													headerToolbar : [
														new sap.m.Toolbar({
															content: [
																new sap.m.Title({text:'Alert density map'})
															]
														})																
													],
													content:[
														new sap.ui.core.HTML({
															content: '<iframe width="100%" height="600px" src="http://www.openheatmap.com/embed.html?map=PerloffTanseyUnquarrelled" ></iframe>'
														})
													]
												})
											]
										})
									]
								})
							]
						})
					]
				})
			]
 		})
	}
});