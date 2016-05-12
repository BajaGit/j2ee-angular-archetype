#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

(function() {
  'use strict';

  angular
    .module('${artifactId}')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig(${symbol_dollar}stateProvider, ${symbol_dollar}urlRouterProvider) {
    ${symbol_dollar}stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'app/main/main.html',
        controller: 'MainController',
        controllerAs: 'main'
      });

    ${symbol_dollar}stateProvider.otherwise("home");
  }

})();
