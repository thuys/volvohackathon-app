sap.ui.jsview("de-truckbackers.Login", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf de-truckbackers.Login
	*/ 
	getControllerName : function() {
		return "de-truckbackers.Login";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf de-truckbackers.Login
	*/ 
	createContent : function(oController) {
 		return new sap.m.Page({
 			showHeader: false,
			content: [
				new sap.m.HBox({
					width: "100%",
					height: "100%",
					justifyContent: "Center",
					items: [
						new sap.m.VBox({
							height: "100%",
							justifyContent: "Center",
							items: [
								new sap.m.Panel({
									width: "600px",
									height: "400px",
									content: [
										new sap.m.VBox({
											items: [
												new sap.m.HBox({
													items: [
														new sap.m.Text({
															text: "Field:"
														}),
														new sap.m.Input({})
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
		});
	}

});