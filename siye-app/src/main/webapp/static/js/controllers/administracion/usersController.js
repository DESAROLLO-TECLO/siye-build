/**
 * @title           :* usersController **
 * @author          :CésarGómez
 * @modified        :CésarGómez
 * @createdate      :26/Dic/2018
 * @modifieddate    :22/Ene/2019
 * @version         :1.1.7
 */
angular.module(appTeclo).controller('usersController',
    function($rootScope,$scope,$timeout,$filter,$document,
        jwtService,storageService,userService,showAlert,growl,predata)
    {
        /**
         * @title :*DEFINICIÓN DE CONSTANTES*
         * @constants :
         * * CURRENTUSER: Contiene el c+odigo del usuario que está logueado
         * * SELECTYPESEARCH: Contiene el elemento select del tipo de búsqueda
         * * SELECTPROFILE: Contiene el elemento select del perfil
         * * REGEXPEMAIL: Expresión regular para el patrón que se usa en el campo de correo
         */
        const CURRENTUSER = jwtService.getPlacaUsuario(storageService.getToken());
        const SELECTYPESEARCH = $('#select2-typeSearch-container');
        const SELECTSEARCHALL = $('#select2-searchAll-container');
        const SELECTPROFILE = $('#select2-profile-container');
        const REGEXPEMAIL = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,6}$/;
        
        /** 
         * @title :*DEFINICIÓN DE FLAGS (Banderas)*
         * @flags :
         * * isModalUsers: Mostrar/Ocultar el modal de búsqueda y registro de usuarios
         * * isPromise: Mostrar/Ocultar la plantilla falsa de usuarios que simula la carga
         * * isGridMode: Mostrar/Ocultar el modo gráfico
         * * isListMode: Mostrar/Ocultar el modo lista
         * * isTypeSearchProfile: Mostrar/Ocultar el combo de perfiles
         * * isModalUsersEdit: Mostrar/Ocultar el modal para editar usuarios
         * * isFirstModal: Indicador para detectar si es la primera vez que se entra a la funcionalidad
         * * isSearchTab: Mostrar/Ocultar la pestaña de búsqueda de usuarios
         * * isNewTab: Mostrar/Ocultar la pestaña de registro de usuario
         * * isDataPersonal: Mostrar/Ocultar la pestaña vertical de datos personales en registro de usuario nuevo
         * * isDataUser: Mostrar/Ocultar la pestaña vertical de datos del usuario en registro de usuario nuevo
         * * isDataContact: Mostrar/Ocultar la pestaña vertical de datos de contacto en registro de usuario nuevo
         * * isPassword: Mostrar/Ocultar el campo de contraseña personalizada
         * * isButtonMode: Habilitar/Deshabilitar el botón de cambio de modo de vista
         */
        $scope.flags = new Object({
            isModalUsers: false,
            isPromise: false,
            isGridMode: true,
            isListMode: false,
            isTypeSearchAll: true,
            isTypeSearchProfile: false,
            isModalUsersEdit: false,
            isFirstModal: true,
            isSearchTab: true,
            isNewTab: false,
            isDataPersonal: true,
            isDataUser: false,
            isDataContact: false,
            isPassword: false,
            isButtonMode: true
        });

        $timeout(function() {
            $scope.flags.isModalUsers = true;
        }, 300);

        /**
         * @scopes :
         * * $root.isChargedFile: En base a esta bandera se detecta si se ha cargado imagen al registro de usuario
         * * typeInputPasswordCU: Se define el tipo de dato para el input de contraseña
         * * eventCardSelected: Contiene el evento del usuario seleccionado actualmente (Se inicializa indefinido para validar)
         */
        $scope.$root.isChargedFile = false;
        $scope.typeInputPasswordCU = 'password';
        $scope.eventCardSelected = undefined;

        /** 
         * @title :*DEFINICIÓN DE MODELOS*
         * @models :
         * * userVO: Tiene las propiedades para un usuario nuevo
         * * searchVO: Tiene las propiedades para la búsqueda de usuarios
         * * gridOptions: Tiene las propiedades para la paginación del grid y la tabla de usuarios
        */
        $scope.userVO = new Object({
            foto: {
                lbFoto: ""
            },
            stActivo: true,
            patternEmail: REGEXPEMAIL
        });
        $scope.searchVO = new Object({
            options: predata.typeSearch,
            optionsForAll: predata.searchForAll,
            profiles: predata.profiles,
            profilesCat: new Array(),
            listFake: new Array(),
            typeSearch: new Object(),
            typeFind: 1,
            searchInput: "",
            searchAll: new Object(),
            profile: new Object(),
            menus: new Array(),
            arrayUsersOriginal: new Array(),
            arrayUsers: new Array(),
            arrayUsersTmp: new Array()
        });
        $scope.gridOptions = new Object({
            data: new Array(),
            urlSync: false,
            pagination: {
                itemsPerPage: '6'
            }
        });

        /**
         * @desciption : 
         * * ARREGLO DE OBJETOS PARA IMÁGENES DE AVATARS
         */
        $scope.arrayAvatars = [
            {idAvatar:1,srcAvatar:'static/dist/img/male-1.png',isSelected:false},
            {idAvatar:2,srcAvatar:'static/dist/img/male-2.png',isSelected:false},
            {idAvatar:3,srcAvatar:'static/dist/img/female-3.png',isSelected:false},
            {idAvatar:4,srcAvatar:'static/dist/img/female-4.png',isSelected:false}
        ];

        /**
         * @title           :*EJECUTAR FUNCIONALIDADES AL INICIAR EL CONTROLADOR*
         * @author          :CésarGómez
         * @modified        :CésarGómez
         * @createdate      :26/Dic/2018
         * @modifieddate    :20/Ene/2019
         * @version         :1.2
         * @description     :
         * * Se setea por default la primera opción al componente select de tipo de búsqueda
         * * Se setea por default la primera opción al componente select cuando se selecciona por TODOS
         * * Se mapea el arreglo de perfiles activos/inactivos para llenar el catálogo de perfiles activos
         * * Se crea una iteración con map para generar la plantilla de carga al realizar la petición de búsqueda de usuarios
         */
        initController = function() {

            $scope.searchVO.typeSearch = $scope.searchVO.options[0];
            SELECTYPESEARCH.text($scope.searchVO.options[0].descripcion);

            $scope.searchVO.searchAll = $scope.searchVO.optionsForAll[0];
            SELECTSEARCHALL.text($scope.searchVO.searchAll.descripcion);

            predata.profiles.map(profile => {
                if(profile.stActivo === 1) {
                    $scope.searchVO.profilesCat.push(profile);
                }
            });

            for(i = 0; i < 6; i++) {
                $scope.searchVO.listFake.push(i);
            }
        };

        /**
         * @title           :*CAMBIAR EL TIPO DE BÚSQUEDA DE USUARIOS*
         * @author          :CésarGómez
         * @modified        :CésarGómez
         * @createdate      :07/Ene/2019
         * @modifieddate    :08/Ene/2019
         * @version         :1.3
         * @params          :Object model
         * @description     :
         * * Se muestra un input si es por Nombre, Apellido o Usuario
         * * Se muestra un combo si es por Perfil y se setea por default la primera opción
         * * Se valida por código de tipo de búsqueda para habilitar el select de activos inactivos de TODOS
         */
        $scope.changeTypeSearch = function(model) {

            if(model.codigo === 2 || model.codigo === 3 || model.codigo === 4) {
                $scope.flags.isTypeSearchProfile = false;
                $scope.flags.isTypeSearchAll = false;

                $scope.searchVO.searchInput = "";

                if($scope.formUsers.searchValue !== undefined) {
                    $scope.formUsers.searchValue.$setPristine();
                }

            } else {
                if(model.codigo === 1) {
                    $scope.flags.isTypeSearchAll = true;
                    $scope.flags.isTypeSearchProfile = false;

                    $scope.searchVO.searchAll = $scope.searchVO.optionsForAll[0];
                    SELECTSEARCHALL.text($scope.searchVO.searchAll.descripcion);

                } else {
                    $scope.flags.isTypeSearchAll = false;
                    $scope.flags.isTypeSearchProfile = true;

                    $scope.searchVO.profile = $scope.searchVO.profiles[0];
                    SELECTPROFILE.text($scope.searchVO.profile.nbPerfil);
                }
            }
        };

        /**
         * @title           :*MOSTRAR/OCULTAR PESTAÑA DE BÚSQUEDA O NUEVO REGISTRO DE USUARIO*
         * @author          :CésarGómez
         * @modified        :----------
         * @createdate      :08/Ene/2019
         * @modifieddate    :--/--/----
         * @version         :1.0
         * @params          :String tab
         */
        $scope.toggleTabUser = function(tab) {
            switch(tab) {
                case 'search':
                    $scope.flags.isSearchTab = true;
                    $scope.flags.isNewTab = false;
                    $scope.flags.isPassword = false;
                    $scope.toggleDataNew('personal');
                    break;
                case 'new':
                    $scope.flags.isNewTab = true;
                    $scope.flags.isSearchTab = false;
                    $scope.userVO.perfil = $scope.searchVO.profiles[0];
                    break;
            }
        };

        /**
         * @title           :*REALIZAR LA BÚSQUEDA DE USUARIOS*
         * @author          :CésarGómez
         * @modified        :CésarGómez
         * @createdate      :11/Ene/2019
         * @modifieddate    :18/Ene/2019
         * @version         :1.4
         * @description     :
         * * Se valida el formulario
         * * Cuando es por Nombre, Apellido o Usuario se realiza la petición getUsersByType
         * * Cuando es por Perfil se realiza la petición getUsersByProfile
         * * Se validan los resultados
         * * Cuando no se encuentran registros se manda mensaje y se regresan las banderas al estado original de búsqueda
         * * Cuando hay registros se llenan los arreglos de usuarios y se cambia el estado de las banderas para mostrar los resultados
         */
        $scope.searchUsers = function() {

            const VALIDATERESPONSE = function(array) {
                if(array.length === 0) {

                    $scope.gridOptions.data = new Array();
                    $scope.searchVO.arrayUsersOriginal = new Array();
                    $scope.searchVO.arrayUsers = new Array();
                    $scope.searchVO.arrayUsersTmp =  new Array();

                    showAlert.aviso("No se encontraron registros",
                        //Aceptar
                        function() {
                            $scope.flags.isPromise = false;
                            $scope.flags.isFirstModal = true,
                            $scope.flags.isModalUsers = true;
                        }
                    );

                } else {

                    $scope.gridOptions.data = array;
                    $scope.searchVO.arrayUsersOriginal = angular.copy(array);
                    $scope.searchVO.arrayUsers = array;
                    $scope.searchVO.arrayUsersTmp =  array;
                    $scope.flags.isPromise = false;
                    $scope.flags.isModalUsers = false;

                    for(let user of array) {
                        if(user.cdUsuario === CURRENTUSER) {
                            user.isCurrentUser = true;
                        } else {
                            user.isCurrentUser = false;
                        }
                    }
                }
            };

            if($scope.formUsers.$invalid) {
                showAlert.requiredFields($scope.formUsers, 'modal');
            } else {

                let params = new Object();

                $scope.flags.isPromise = true;
                $scope.flags.isModalUsers = false;

                if($scope.flags.isTypeSearchProfile) {

                    params = {profile: $scope.searchVO.profile.cdPerfil};

                    userService.getUsersByProfile(params).success(function(users) {

                        VALIDATERESPONSE(users);

                    }).error(function(e) {
                        if(e !== null) {showAlert.error(`Ocurrió un error en la petición ${e.message}`);}
                        else {showAlert.error('Falló la petición');}
                    });

                } else {

                    params = {
                        parameter: $scope.searchVO.typeSearch.codigoString,
                        value: $scope.flags.isTypeSearchAll
                                ? $scope.searchVO.searchAll.codigoString
                                : $scope.searchVO.searchInput
                    };

                    userService.getUsersByType(params).success(function(users) {

                        VALIDATERESPONSE(users);

                    }).error(function(e) {
                        if(e !== null) {showAlert.error(`Ocurrió un error en la petición ${e.message}`);}
                        else {showAlert.error('Falló la petición');}
                    });
                }

                $scope.flags.isFirstModal = false;
            }
        };

        /**
         * @title           :*MOSTRAR/OCULTAR LAS PESTAÑAS DEL REGISTRO DE USUARIOS*
         * @author          :CésarGómez
         * @modified        :----------
         * @createdate      :10/Ene/2019
         * @modifieddate    :--/--/----
         * @version         :1.0
         * @params          :String tab
         * @description     :
         * * Datos personales: true | false
         * * Datos del usuario: true | false
         * * Datos de contacto: true | false
         */
        $scope.toggleDataNew = function(tab) {
            switch(tab) {
                case 'personal':
                    $scope.flags.isDataPersonal = true;
                    $scope.flags.isDataUser = false;
                    $scope.flags.isDataContact = false;
                    break;
                case 'user':
                    $scope.flags.isDataPersonal = false;
                    $scope.flags.isDataUser = true;
                    $scope.flags.isDataContact = false;
                    break;
                case 'contact':
                    $scope.flags.isDataPersonal = false;
                    $scope.flags.isDataUser = false;
                    $scope.flags.isDataContact = true;
                    break;
            }
        };

        /**
         * @title           :*ELIMINAR LA FOTO CARGADA O EL AVATAR SELECCIONADO*
         * @author          :CésarGómez
         * @modified        :----------
         * @createdate      :09/Ene/2019
         * @modifieddate    :--/--/----
         * @version         :1.0
         * @description     :
         * * Se vacía la propiedad foto del objeto userVO
         * * Se recorre con un map el arreglo de avatars y por cada elemento se define en false la propiedad isSelected
         * * Se define en false la variable global $rootScope.isChargedFile
         */
        $scope.deletePhoto = function () {

            $scope.userVO.foto = new Object();

            $scope.arrayAvatars.map( avatar => {
                avatar.isSelected = false;
            });

            $scope.$root.isChargedFile = false;
        };

        /**
         * @title           :*CAMBIAR EL AVATAR O FOTO DE USUARIO*
         * @author          :CésarGómez
         * @modified        :CésarGómez
         * @createdate      :09/Ene/2019
         * @modifieddate    :14/Ene/2019
         * @version         :1.1
         * @params          :Object avatar
         * @description     :
         * * Se valida cuando ya se cargó una imagen y se quiere seleccionar uno de los avatars por defecto
         * * Se llama la función que convierte la imagen en blob string para mostrarla con el ng-src
         */
        $scope.toggleAvatar = function(avatar) {
            
            const REPLACEAVATAR = function() {

                $scope.arrayAvatars.map( avtr => {
                    avtr.isSelected = false;
                });
                
                avatar.isSelected = true;
    
                getbs64(avatar.srcAvatar, function(blob) {
                    $scope.userVO.foto.lbFoto = blob;
                });
    
                $scope.$root.isChargedFile = false;
            }

            if($scope.$root.isChargedFile) {
                showAlert.confirmacion(
                    $scope.mensajeModal("¿Desea reemplazar la imagen cargada por el avatar seleccionado?"),
                    //Aceptar
                    REPLACEAVATAR
                );
            } else {
                REPLACEAVATAR();
            }
        };

        /**
         * @title           :*CAMBIAR ENTRE CONTRASEÑA PERSONALIZADA O POR DEFECTO*
         * @author          :CésarGómez
         * @modified        :----------
         * @createdate      :10/Ene/2019
         * @modifieddate    :--/--/----
         * @version         :1.0
         * @description     :
         * * Cuando flags.isPassword es false se vacía el model userVO.nbContrasenia
         * * Cuando flags.isPassword es true se igualan los modelos userVO.cdUsuario y userVO.nbContrasenia
         */
        $scope.customPassword = function() {

            if($scope.flags.isPassword === false) {

                $scope.flags.isPassword = true;
                $scope.userVO.nbContrasenia = '';

            } else {

                $scope.flags.isPassword = false;
                $scope.userVO.nbContrasenia = $scope.userVO.cdUsuario;

            }
        };

        /**
         * @Note :*Esta función está temporalmente fuera de acción*
         * @title           :*MOSTRAR LOS MENUS CORRESPONDIENTES AL PERFIL SELECCIONADO*
         * @author          :CésarGómez
         * @modified        :----------
         * @createdate      :15/Ene/2019
         * @modifieddate    :--/--/----
         * @version         :1.0
         * @params          :String profilename
         * @description     :
         * * Se configura el menu para mostrar módulo y funcionalidad correspondiente al perfil recibido
         */
        $scope.getMenusbyProfile = function(profilename) {

            let configureMenu = function(menuprofile) {

                let menus = new Array();
    
                for(let menu of menuprofile) {
    
                    if (menu.nuMenuSuperior === 0) {
    
                        menu.submenus = new Array();
    
                        for (let submenu of menuprofile) {
                            if (menu.idMenu === submenu.nuMenuSuperior) {
                                menu.submenus.push(submenu);
                            }
                        }
    
                        menus.push(menu);
                    }
                }
    
                return menus;
            };

            if(profilename !== undefined) {

                userService.getMenusByProfile(profilename).success(function(menus) {

                    $scope.searchVO.menus = configureMenu(menus);

                }).error(function(e) {
                    if(e !== null) {showAlert.error(`Ocurrió un error en la petición ${e.message}`);}
                    else {showAlert.error('Falló la petición');}
                });

            } else {
                $scope.searchVO.menus = new Array();
            }
        }

        /**
         * @title           :*GUARDAR USUARIO*
         * @author          :CésarGómez
         * @modified        :CésarGómez
         * @createdate      :16/Ene/2019
         * @modifieddate    :21/Ene/2019
         * @version         :1.6
         * @params          :Object userVO, String action
         * @description     :
         * * Se valida el formulario
         * * Se ejecuta la petición para guardar el usuario nuevo
         */
        $scope.saveOrUpdateUser = function(userVO, action) {

            if($scope.formUsers.$invalid) {

                showAlert.requiredFields($scope.formUsers, 'modal');

                if($scope.formUsers.nbUsuario.$invalid
                    || $scope.formUsers.nbApaterno.$invalid
                    || $scope.formUsers.nbAmaterno.$invalid) {

                    $scope.toggleDataNew('personal');

                } else {

                    if($scope.formUsers.cdUsuario.$invalid
                        || $scope.formUsers.nbContrasenia.$invalid
                        || $scope.formUsers.perfils.$invalid) {

                        $scope.toggleDataNew('user');

                    } else {

                        if($scope.formUsers.nbEmail.$invalid 
                            || $scope.formUsers.nuTelefono.$invalid) {

                            $scope.toggleDataNew('contact');
                        }
                    }
                }

            } else {

                let saveOrUpdate = function() {

                    userService.saveOrUpdateUser(userVO, action).success(function(user) {
                    
                        switch(action) {
                            case "new":
                                showAlert.aviso("Usario guardado correctamente");
                                break;
                            case "edit":
                                showAlert.aviso("Usuario actualizado correctamente");
                                $scope.flags.isModalUsers = false;
                                $scope.flags.isNewTab = false;
                                $scope.flags.isModalUsersEdit = false;
                                break;
                        }
    
                        $scope.toggleCloseSaveUser('new');
    
                    }).error(function(e){
                        if(e.status === 404) {
                            showAlert.aviso(e.message);
                        } else {
                            showAlert.error(e.message);
                        }
                    });
                };

                let obj = new Object({
                    password: userVO.nbContrasenia,
                    action: 'encrypt'
                });

                userVO.lbFoto = $scope.userVO.foto.lbFoto !== ("" || undefined)
                                ? $scope.userVO.foto.lbFoto.split(',')[1]
                                : null;

                if(action === "edit" && userVO.isCurrentUser) {

                    userService.toggleEncryption(obj).success(function(password) {

                        userVO.nbContrasenia = password;
                        saveOrUpdate();

                    });

                } else {
                    saveOrUpdate();
                }

            }
        };

        /**
         * @title           :*EDITAR USUARIO*
         * @author          :CésarGómez
         * @modified        :CésarGómez
         * @createdate      :17/Ene/2019
         * @modifieddate    :18/Ene/2019
         * @version         :1.1
         * @params          :Object userVO
         * @description     :
         * * Se agrega la propiedad de foto al objeto recibido
         * * Se valida si el usuario es el logueado actualmente para desencriptar su contraseña
         * * Se realiza una copia del objeto recibido
         * * Se switchean los flags para mostrar el modal de edición de usuario (Se reutiliza el formulario de nuevo usuario)
         */
        $scope.editUser = function(userVO) {

            $scope.userTmp = new Object();
            userVO.foto = new Object();
            $scope.userVO = userVO;
            $scope.userVO.foto.lbFoto = userVO.lbFoto == (null || undefined)? "" : `data:image/png;base64,${userVO.lbFoto}`;
            $scope.userVO.patternEmail = REGEXPEMAIL;

            if(userVO.isCurrentUser) {

                let obj = new Object({
                    password: userVO.nbContrasenia,
                    action: 'decrypt'
                });

                userService.toggleEncryption(obj).success(function(password) {

                    userVO.nbContrasenia = password;

                }).error(function(e) {
                    if(e !== null) {showAlert.error(`Ocurrió un error en la petición ${e.message}`);}
                    else {showAlert.error('Falló la petición');}
                });
            }

            angular.copy($scope.userVO, $scope.userTmp);

            $scope.flags.isModalUsers = true;
            $scope.flags.isNewTab = true;
            $scope.flags.isSearchTab = false;
            $scope.flags.isModalUsersEdit = true;

        };

        /**
         * @title           :*ABRIR | CERRAR MODAL DE EDICIÓN O NUEVO USUARIO*
         * @author          :CésarGómez
         * @modified        :----------
         * @createdate      :18/Ene/2019
         * @modifieddate    :--/--/----
         * @version         :1.0
         * @params          :String action
         * @description     :
         * * Se comprueba si se ejecuta el modal de nuevo usuario o edición de usuario
         * * Cuando es nuevo usuario se limpia el formulario y se deja listo para registrar un nuevo usuario
         * * Cuando es edición se comprueba que no se hayan hecho cambios
         * * Cuando es edición se confirma el cierra del modal si se realizaron cambios al usuario
         */
        $scope.toggleCloseSaveUser = function(action) {

            let clearUser = function() {

                $scope.deletePhoto();
                $scope.userVO = new Object({
                    foto: {
                        lbFoto: ""
                    },
                    stActivo: true,
                    patternEmail: REGEXPEMAIL
                });
                $scope.formUsers.$setPristine();
                $scope.userVO.perfil = $scope.searchVO.profiles[0];

                $scope.toggleDataNew('personal');

                $scope.flags.isPassword = false;

                if(!$scope.flags.isFirstModal) {
                    $timeout(function() {
                        $scope.flags.isModalUsers = false;
                    }, 200);
                    $('.modaluser__form').removeClass('modalAnimateIn').addClass('modalAnimateOut');
                }
            };

            switch(action) {
                case 'new':
                    clearUser();
                    break;
                case 'edit':

                    if(angular.equals($scope.userTmp, $scope.userVO)) {
                        $scope.flags.isNewTab = false;
                        $scope.flags.isModalUsersEdit = false;
                        $scope.flags.isPassword = false;
                        clearUser();
                    } else {
                        showAlert.confirmacion("Esta acción descartará todos los cambios realizados. ¿Desea continuar?",
                            //Aceptar
                            function() {

                                $scope.flags.isNewTab = false;
                                $scope.flags.isModalUsersEdit = false;
                                $scope.flags.isPassword = false;

                                $scope.gridOptions.data = $scope.gridOptions.data.map( user => {
                                    
                                    if(user.idUsuario === $scope.userTmp.idUsuario) {
                                        Object.assign(user, $scope.userTmp);
                                    }

                                    return user;
                                });

                                clearUser();
                            },
                            //Cancelar
                            function() {
                                $scope.flags.isModalUsers = true;
                                $scope.flags.isNewTab = true;
                                $scope.flags.isModalUsersEdit = true;
                            }
                        );
                    }
                    break;
            }
        };

        /**
         * @title           :*MOSTRAR/OCULTAR MODAL DE BÚSQUEDA O NUEVO SEGÚN LA ACCIÓN MANDADA*
         * @author          :CésarGómez
         * @modified        :----------
         * @createdate      :14/Ene/2019
         * @modifieddate    :--/--/----
         * @version         :1.0
         * @params          :String action
         */
        $scope.toggleModal = function(action) {
            switch(action) {
                case 'search':
                    $scope.flags.isModalUsers = true;
                    $scope.flags.isSearchTab = true;
                    $scope.flags.isNewTab = false;
                    break;
                case 'new':
                    $scope.flags.isModalUsers = true;
                    $scope.flags.isNewTab = true;
                    $scope.flags.isSearchTab = false;
                    break;
                default:
                    $scope.flags.isModalUsers = false;
            }
        };

        /**
         * @title           :*MOSTRAR EL MODO DE VISTA (MOSAICO O LISTA) DE LOS USUARIOS ENCONTRADOS EN LA BÚSQUEDA*
         * @author          :CésarGómez
         * @modified        :----------
         * @createdate      :14/Ene/2019
         * @modifieddate    :--/--/----
         * @version         :1.0
         * @params          :String mode
         */
        $scope.toggleModeView = function(mode) {
            switch(mode) {
                case 'grid':
                    $scope.flags.isGridMode = true;
                    $scope.flags.isListMode = false;
                    break;
                case 'list':
                    $scope.flags.isListMode = true;
                    $scope.flags.isGridMode = false;
                    break;
                default:
                    $scope.flags.isGridMode = true;
                    $scope.flags.isListMode = false;
            }

            $scope.flags.isButtonMode = false;

            $timeout(function() {
                $scope.flags.isButtonMode = true;
            }, 500);
        };

        /**
         * @title           :*CAMBIAR EL TIPO DE DATO EN EL INPUT DE CONTRASEÑA*
         * @author          :CésarGómez
         * @modified        :----------
         * @createdate      :16/Ene/2019
         * @modifieddate    :--/--/----
         * @version         :1.0
         * @description     :
         * * Permite ver la contraseña si es el usuario logueado dejando el input en type="text"
         * * Oculta la contraseña para los demás usuarios dejando el input en type="password"
         */
        $scope.toggleViewPassword = function() {
            $scope.typeInputPasswordCU = $scope.typeInputPasswordCU === 'password'
                ? 'text'
                : 'password';
        }

        /**
         * @title           :*VISTA DE TARJETA DE USUARIO EN MODO MOSAICO (GRID)*
         * @author          :CésarGómez
         * @modified        :CésarGómez
         * @createdate      :11/Ene/2019
         * @modifieddate    :14/Ene/2019
         * @version         :1.1
         * @params          :Event event, String option
         * @description     :
         * * Se cambia de vista frontal a vista anversa, y viceversa de acuerdo a la opción mandada
         * * Se lanza la ejecución de la función flipcard para realizar el cambio de vista (front | back)
         */
        $scope.toggleUserCard = function(event, option) {

            if($scope.eventCardSelected !== undefined) {
                flipCard($scope.eventCardSelected, 'close');
            }

            flipCard(event, option);
        };

        /**
         * @title           :*REALIZAR LA VOLTERETA DE LOS BOX DE CADA USUARIO (MODO MOSAICO)*
         * @author          :CésarGómez
         * @modified        :CésarGómez
         * @createdate      :11/Ene/2019
         * @modifieddate    :14/Ene/2019
         * @version         :1.2
         * @params          :Event event, String option
         * @description     :
         * * Se recibe el evento del box de usuario
         * * Se obtiene el target y el parent del box
         * * Se valida que el parent contenga la clase flip-container
         * * De acuerdo a la opción recibida se voltea el box de anverso a reverso o viceversa
         */
        flipCard = function(event, option) {

            let parent = event.target.parentNode;

            while (parent) {

                parent = parent.parentNode;

                if(parent.classList.contains('flip-container')) {

                    switch(option) {
                        case 'open':
                            parent.classList.add('flipped');
                            parent.parentNode.parentNode.classList.add('z-index-1');
                            break;
                        case 'close':
                            parent.classList.remove('flipped');
                            parent.parentNode.parentNode.classList.remove('z-index-1');
                            break;
                        default:
                            break;
                    }

                    break;
                }
            }

            $scope.eventCardSelected = event;
        };

        /**
         * @title           :*CAMBIAR EL PERFIL DEL USUARIO*
         * @author          :CésarGómez
         * @modified        :CésarGómez
         * @createdate      :16/Ene/2019
         * @modifieddate    :16/Ene/2019
         * @version         :1.3
         * @params          :Object userVO, Number idSelect
         * @description     :
         * * Solicita confirmación para actualizar el perfil
         * * Consume el servicio de actualizar en caso de ser aceptada la confirmación
         * * Cuando la búsqueda de usuarios fue diferente de perfiles se setea el nuevo valor al arreglo original
         * * Cuando la búsqueda es por perfiles se actualiza la lista de usuarios para descartar al usuario actualizado
         * * Cuando la petición falla o se cancela el cambio, se revierten los cambios del perfil
         */
        $scope.changeProfilebyUser = function (userVO, idSelect) {

            let revertChanges = function() {

                for(let user of $scope.searchVO.arrayUsersOriginal) {

                    if(user.idUsuario === userVO.idUsuario) {

                        userVO.perfil = user.perfil;
                        $(`#select2-${idSelect}-container`).text(userVO.perfil.nbPerfil);
                        break;
                    }
                }
            };

            if(userVO.stActivo === 0) {

                growl.error('', {title:"El usuario debe estar habilitado para realizar esta acción", ttl: 5000});
                revertChanges();

            } else {

                showAlert.confirmacion(`Seguro que desea cambiar el perfil del usuario: 
                ${userVO.nbUsuario} ${userVO.nbApaterno} ${userVO.nbAmaterno}`,
    
                // Aceptar
                function() {
    
                    userService.saveOrUpdateUser(userVO, 'edit').success(function(user) {
    
                        if($scope.searchVO.typeSearch.codigo !== 5) {
                            for(let user of $scope.searchVO.arrayUsersOriginal) {

                                if(user.idUsuario === userVO.idUsuario) {
                                    user.perfil = userVO.perfil;
                                    break;
                                }
                            }
                        } else {
                            $scope.searchUsers();
                        }
    
                        growl.success('', {title: 'Perfil actualizado correctamente'});
                        flipCard($scope.eventCardSelected, 'close');
                        userVO.fhModificacion = user.fhModificacion;
                        
                    }).error(function(e) {
                        if(e !== null) {showAlert.error(`Ocurrió un error en la petición ${e.message}`);}
                        else {showAlert.error('Falló la petición');}
                        revertChanges();
                    });
    
                },
    
                // Cancelar
                function() {
                    revertChanges();
                });
            }
        };
        
        /**
         * @title           :*CAMBIAR EL ESTATUS DEL USUARIO SELECCIONADO*
         * @author          :CésarGómez
         * @modified        :----------
         * @createdate      :16/Ene/2019
         * @modifieddate    :--/--/----
         * @version         :1.0
         * @params          :Number newval, Number oldval, Object userVO
         * @description     :
         * * Solicita confirmación para actualizar el estatus
         * * Cuando se actualiza el estatus se manda a llamar la función flipCard
         * * Si se cancela el cambio, se regresa el estatus
         */
        $scope.changeStatusUser = function(newval, oldval, userVO) {

            userVO.stActivo = oldval;

            showAlert.confirmacion(`Seguro que desea cambiar el estatus del usuario
                ${userVO.nbUsuario} ${userVO.nbApaterno} ${userVO.nbAmaterno}`,
                //Aceptar
                function() {
                
                    userVO.stActivo = newval;

                    userService.updateStatusUser(userVO).success(function(user){

                        flipCard($scope.eventCardSelected, 'close');
                        growl.success('', {title: 'Estatus actualizado correctamente'});
                        userVO.fhModificacion = user.fhModificacion;

                    }).error(function(e) {
                        if(e !== null) {showAlert.error(`Ocurrió un error en la petición ${e.message}`);}
                        else {showAlert.error('Falló la petición');}
                    });
                },
                //Cancelar
                function() {
                    userVO.stActivo = oldval;
                });
        };

        /**
         * @title           :*RESETEAR LA CONTRASEÑA DEL USUARIO*
         * @author          :CésarGómez
         * @modified        :CésarGómez
         * @createdate      :16/Ene/2019
         * @modifieddate    :17/Ene/2019
         * @version         :1.0
         * @params          :Object userVO
         */
        $scope.restorePassword = function(userVO) {

            if(userVO.stActivo === 0) {

                growl.error('', {title:"El usuario debe estar habilitado para realizar esta acción", ttl: 5000});

            } else {

                showAlert.confirmacion(`Seguro que desea restaurar la contraseña del usuario
                    ${userVO.nbUsuario} ${userVO.nbApaterno} ${userVO.nbAmaterno}`,
                    //Aceptar
                    function() {
                        userService.restorePasswordbyUser(userVO).success(function(user) {

                            growl.success('', {title:"Contraseña restaurada correctamente"});

                        }).error(function(e) {
                            if(e !== null) {showAlert.error(`Ocurrió un error en la petición ${e.message}`);}
                            else {showAlert.error('Falló la petición');}
                        });
                    }
                );
            }
        };
        
        /**
         * @title           :*FILTRO PARA LOS USUARIOS*
         * @author          :CésarGómez
         * @modified        :CésarGómez
         * @createdate      :11/Ene/2019
         * @modifieddate    :18/Ene/2019
         * @version         :1.3
         * @params          :String model
         * @description     :
         * * Se valida que el model no venga vacío para proceder con el filtrado
         * * Si viene vacío el model se persiste el arreglo de usuarios
         * * Se recorre el arreglo de usuarios temporal para crear copias de sus propiedades
         * * Se ponen en minúscula los valores de las propiedades copiadas
         * * Se valida que model coincida con el valor de alguna de las propiedades copiadas
         * * Si hay coincidencias se sobreescribe el arreglo de usuarios
         */
        $scope.filterRefresh = function(model) {
            // $scope.gridOptions.data = $filter('filter')($scope.searchVO.arrayUsersTmp, model);
            // $scope.gridOptions.data = $filter('filter')($scope.searchVO.arrayUsersTmp, {nbUsuario: model});
            // $scope.searchVO.arrayUsers = $scope.gridOptions.data;

            if(model !== "") {

                let restoreValues = function(user, obj) {
                    user.nbUsuario = obj.nbUsuario;
                    user.nbApaterno = obj.nbApaterno;
                    user.nbAmaterno = obj.nbAmaterno;
                    user.cdUsuario = obj.cdUsuario;
                    user.perfil.nbPerfil = obj.nbPerfil;
                }
    
                $scope.searchVO.arrayUsers = new Array();
    
                for(let user of $scope.searchVO.arrayUsersTmp) {
    
                    let nbUserTmp = angular.copy(user.nbUsuario);
                    let nbApaternTmp = angular.copy(user.nbApaterno);
                    let nbAmaternTmp = angular.copy(user.nbAmaterno);
                    let cdUsuarioTmp = angular.copy(user.cdUsuario);
                    let nbProfileTmp = angular.copy(user.perfil.nbPerfil);
                    
                    model = model.toLowerCase();
                    user.nbUsuario = user.nbUsuario.toLowerCase();
                    user.nbApaterno = user.nbApaterno.toLowerCase();
                    user.nbAmaterno = user.nbAmaterno.toLowerCase();
                    user.cdUsuario = user.cdUsuario.toLowerCase();
                    user.perfil.nbPerfil = user.perfil.nbPerfil.toLowerCase();
    
                    let obj = new Object({
                        nbUsuario: nbUserTmp,
                        nbApaterno: nbApaternTmp,
                        nbAmaterno: nbAmaternTmp,
                        cdUsuario: cdUsuarioTmp,
                        nbPerfil: nbProfileTmp
                    });

                    if(user.nbUsuario.indexOf(model) !== -1 
                        || user.nbApaterno.indexOf(model) !== -1 
                        || user.nbAmaterno.indexOf(model) !== -1
                        || user.cdUsuario.indexOf(model) !== -1 
                        || user.perfil.nbPerfil.indexOf(model) !== -1) {

                        restoreValues(user, obj);
    
                        $scope.searchVO.arrayUsers.push(user);

                    } else {
                        restoreValues(user, obj);
                    }
    
                }
    
                $scope.gridOptions.data = $scope.searchVO.arrayUsers;

                $timeout(function() {
                    for(let user of $scope.gridOptions.data) {
                        let idSelectProfile = `#select2-userProfile${user.idUsuario}-container`;
                        $(idSelectProfile).text(user.perfil.nbPerfil);
                    }
                }, 10000);

            } else {
                $scope.gridOptions.data = $scope.searchVO.arrayUsersTmp;
                $scope.searchVO.arrayUsers = $scope.gridOptions.data;
            }
        };
        
        /**
         * @title           :*CONVERTIR ARCHIVO DE IMAGEN EN BLOB STRING*
         * @author          :CésarGómez
         * @modified        :----------
         * @createdate      :09/Ene/2019
         * @modifieddate    :--/--/----
         * @version         :1.0
         * @params          :String urlFile, Function callback
         * @return          :is void
         * @description     :
         * * Se lee el archivo correspondiente al urlFile
         * * Se obtiene la cadena y se convierte a blob
         * * Se retorna al callback el blob leído
         * * Se metió la lectura y ejecución del callback en un evalAsync para sincronizar el cambio instantáneamente (sin retardos)
         */
        getbs64 = function(urlFile, callback) {

            let xhr = new XMLHttpRequest();

            xhr.onload = function () {

                let reader = new FileReader();

                reader.onloadend = function () {
                    $rootScope.$evalAsync(function() {
                        callback(reader.result);
                    });
                };

                reader.readAsDataURL(xhr.response);
            };

            xhr.open('GET', urlFile);
            xhr.responseType = 'blob';
            xhr.send();
        };

        /**
         * @title           :*ESCUCHAR LOS CAMBIOS DEL CÓDIGO DE USUARIO*
         * @author          :CésarGómez
         * @modified        :----------
         * @createdate      :09/Ene/2019
         * @modifieddate    :--/--/----
         * @version         :1.0
         * @params          :String nv, String ov
         * @description     :
         * * Cuando se detecte un cambio en el código de usuario nuevo, se actualiza el campo de contraseña por el mismo valor
         */
        $scope.$watch('userVO.cdUsuario', function(nv, ov) {
            if(!$scope.flags.isPassword && !$scope.flags.isModalUsersEdit ) {
                $scope.userVO.nbContrasenia = nv;
            }
        });

        /**
         * @title           :*ESCUCHAR EL CAMBIO DE LA PROPIEDAD LBFOTO*
         * @author          :CésarGómez
         * @modified        :CésarGómez
         * @createdate      :09/Ene/2019
         * @modifieddate    :10/Ene/2019
         * @version         :1.2
         * @params          :String nv, String ov
         * @description     :
         * * Se hace un mapeo del arreglo de avatars para verificar la selección
         */
        $scope.$watch('userVO.foto.lbFoto', function(nv, ov) {
            $scope.arrayAvatars.map( avtr => {
                if(avtr.isSelected && $scope.$root.isChargedFile) {
                    avtr.isSelected = false;
                }
            });
        });

        /**
         * @title           :*SE LANZA LA FUNCIÓN AL INICIAR EL CONTROLADOR*
         * @author          :CésarGómez
         * @modified        :----------
         * @createdate      :07/Ene/2019
         * @modifieddate    :--/--/----
         * @version         :1.0
         */
        initController();

});