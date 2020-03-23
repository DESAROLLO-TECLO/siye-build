/*
 * Author: César Gómez
 * Directive: dragNdrop-directive
 * Versión: 1.0.0
 */

angular.module(appTeclo).directive('dndLists', function($timeout) {
	return {
		restrict: 'E',
		templateUrl: 'views/templatedirectivas/dragNdrop-lists.html',
		replace: true,
		scope: {
			col: '@',
			maxHeight:'@',
			model: '=?',
			nbList: '@',
			nbItem: '@',
			nbTexts:'@',
			nbLabels: '@',
			flagSuccess: '=?',
			modelAux: '=',
			requiredList: '=',
			imgCursor: "@"
		},
		link: function($s, $e, $a) {
			
			var listNames = 'listName',
				items	 = 'items',
				labels	 = 'label';
			
			// Limpiar los arregos para quitar espacios en blanco
			cleanArray = function(str) {
				
				var arr = str.split(',');
				
				for(let pos in arr) {
					arr[pos] = arr[pos].trim();
				}
				
				return arr;
			}
			
			// Escuchar cambios en el modelo principal
			$s.$watch('model', function() {
				
				// Validar si la bandera que se cambia al tener estatus success está en true
				if($s.flagSuccess) {
					
					$s.listModel = angular.copy($s.model);
					$s.txs = cleanArray($s.nbTexts);
					$s.lbs = cleanArray($s.nbLabels);
					
					// Modificar los nombres de las propiedades del model principal para usarlos en la directiva
					$s.listModel = $s.listModel.map( item => {
						
						if(item !== null) {
							
							var _listName = item[$s.nbList],
							_items	 = item[$s.nbItem];
						
							delete item[$s.nbList];
							delete item[$s.nbItem];
							
							item[listNames] = _listName;
							item[items] = _items;
							
							item[items] = item[items].map(label => {
								
								for(var i in $s.lbs) {
									
									var _labels = label[$s.lbs[i]];
									
									delete label[$s.lbs[i]];
									
									label[labels+i] = _labels;
								}
								
								return label;
							});
						} else {
							return;
						}
						
						return item;
					});
					
					$s.originalListModel = angular.copy($s.listModel);
					$s.flagSuccess = false;
					$s.btnReset = true;
				}
				
			}, true);
			    
			$s.getSelectedItemsIncluding = function(list, item) {
				item.selected = true;
				return list.items.filter(function(item) { return item.selected; });
			};
			
			$s.onDragstart = function(list, event) {
				
				list.dragging = true;
				
				if (event.dataTransfer.setDragImage) {

					var img = new Image()

					img.src = $s.imgCursor;
					event.dataTransfer.setDragImage(img, 0, 0);
		    	}
				
				saveChanges();
		    };
		    
		    $s.onDrop = function(list, items, index) {
		    	
		    	list.originalList = false;
		    	
		    	angular.forEach(items, function(item) {
		    		item.selected = false;
		    	});
		    	
		    	list.items = list.items.slice(0, index).concat(items).concat(list.items.slice(index));
		    	
		    	saveChanges();
		    	
		    	return true;
		    }
		    
		    $s.onMoved = function(list) {
		    	
		    	list.originalList = false;
		    	
		    	list.items = list.items.filter(function(item) {
		    		return !item.selected;
		    	});
		    	
		    	saveChanges();
		    };
		    
		    // Transferir los objetos de una lista a otra (Únicamente para dos listas)
		    $s.transferItems = function(dir) {

		    	var li  = $('.liRepeat');
		    	
		    	if(dir === 'ltr') {
		    		
		    		if($s.listModel[0].items.length > 0){
		    			
		    			$s.listModel[1].items = $s.listModel[1].items.concat($s.listModel[0].items);
		    			delete $s.listModel[0].items;
		    			$s.listModel[0].items = [];
		    			
		    			let size = $s.listModel[1].items.length;
		    			
		    			li.hide();
		    			
		    			showElement(size, li);
		    		}
		    		
		    	} else if (dir === 'rtl') {
		    		
		    		
		    		if($s.listModel[1].items.length > 0){
		    			
		    			$s.listModel[0].items = $s.listModel[0].items.concat($s.listModel[1].items);
			    		delete $s.listModel[1].items;
			    		$s.listModel[1].items = [];
			    		
			    		let size = $s.listModel[0].items.length;
			    		
			    		li.hide();

			    		showElement(size, li);
		    		}
		    	}
		    	
		    	$s.listModel[0].originalList = false;
		    	$s.listModel[1].originalList = false;
		    	
		    	saveChanges();
		    }
		    
		    // Reestablecer las listas a sus valores por defecto
		    $s.resetModel = function() {
		    	$s.listModel = $s.originalListModel;
		    	$s.originalListModel = angular.copy($s.listModel);
		    	
		    	saveChanges();
		    }
		    
		    // Ver los elementos de las listas a determinado tiempo de acuerdo al número de registros
		    showElement = function(s, l) {
		    	switch(true) {
	    			case (s < 100):
	    				$timeout(function(){l.show();}, 800);
	    				break;
	    			case (s < 300):
	    				$timeout(function(){l.show();}, 1000);
	    				break;
	    			case (s < 500):
	    				$timeout(function(){l.show();}, 1500);
	    				break;
	    			case (s < 800):
	    				$timeout(function(){l.show();}, 2000);
	    				break;
	    			case (s > 800):
	    				$timeout(function(){l.show();}, 3500);
	    				break;
	    		}
		    }
		    
		    // Regresar los nombres de las propiedades del model principal a su nombre original
		    saveChanges = function() {
		    	
		    	$s.model = [];
		    	$s.model = angular.copy($s.listModel);
		    	
		    	$s.model = $s.model.map( item => {

					var _listName = item[listNames],
						_items	 = item[items];
					
					delete item[listNames];
					delete item[items];
					
					item[$s.nbList] = _listName;
					item[$s.nbItem] = _items;
					
					item[$s.nbItem] = item[$s.nbItem].map(label => {
						
						let sz = $s.lbs.length;
						
						switch(sz) {
							case 1:
								var _label0 = label['label0'];
								
								delete label['label0'];
								
								label[$s.lbs[0].trim()] = _label0;
								break;
							case 2:
								var _label0 = label['label0'],
									_label1 = label['label1'];
								
								delete label['label0'];
								delete label['label1'];
								
								label[$s.lbs[0].trim()] = _label0;
								label[$s.lbs[1].trim()] = _label1;
								break;
							case 3:
								var _label0 = label['label0'],
									_label1 = label['label1'],
									_label2 = label['label2'];
							
								delete label['label0'];
								delete label['label1'];
								delete label['label2'];
								
								label[$s.lbs[0].trim()] = _label0;
								label[$s.lbs[1].trim()] = _label1;
								label[$s.lbs[2].trim()] = _label2;
								break;
							default:
								break;
						}
												
						return label;
					});
					
					return item;
				});	
		    }
		    
		    if($s.modelAux !== undefined) {
		    	angular.extend($s.modelAux, {
		    		reset: function(){
		    			$s.resetModel();
		    		},
		    		cleanModel: function() {
		    			$s.model = {};
		    			$s.flagSuccess = false;
		    		}
		    	});
		    }
			
		}
	};
});