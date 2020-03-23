angular.module(appTeclo).service("administracionService", function($http, config) {
 
	/* USUARIOS */
	
	var listaUsuarios = [];
	var listaParams = {};
	var listaPerfiles = [];
	var listaPermisos = [];
	var estatus = null;
	var rowsperpage = 0;
	
	//Getter Setter listaUsuarios
	this.setListaUsuarios = function(lista) {
		listaUsuarios = lista;
	};
	
	this.getListaUsuarios = function() {
		return listaUsuarios;
	}
	
	//Getter Setter listaParams
	this.setListaParams = function(listaP) {
		listaParams = listaP;
	};
	
	this.getListaParams = function() {
		return listaParams;
	}
	
	//Getter Setter listaPerfiles
	this.setListaPerfiles = function(lista) {
		listaPerfiles = lista;
	};
	
	this.getListaPerfiles = function() {
		return listaPerfiles;
	}
	
	//Getter Setter listaPermisos
	this.setListaPermisos = function(lista){
		listaPermisos = lista;
	}
	
	this.getListaPermisos = function(){
		return listaPermisos;
	}
	
	//Getter Setter estatus
	this.getStatus = function() {
		return status;
	}
	
	this.setStatus = function(estado) {
		status = estado;
	};
	
	//Getter Setter rowsperPage
	this.setRowsPerPage = function(rows) {
		rowsperpage = rows;
	};
	
	this.getRowsPerPage = function() {
		return rowsperpage;
	}
	
	
	this.cambiarClave = function (clave, nueva, repetir){
//		return $http.get(config.baseUrl + "/administracionController/cambiarClave",
//				{params:{"clave": clave, "nueva": nueva, "repetir": repetir}});
		
		return null; //Quitar esta línea para desarrollo
	};
});
