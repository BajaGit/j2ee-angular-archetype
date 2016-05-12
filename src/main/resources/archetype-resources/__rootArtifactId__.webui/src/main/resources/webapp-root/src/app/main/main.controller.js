#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

(function() {
  'use strict';

  angular
    .module('${artifactId}')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController( $resource) {
    var vm = this;
    
  }
})();
