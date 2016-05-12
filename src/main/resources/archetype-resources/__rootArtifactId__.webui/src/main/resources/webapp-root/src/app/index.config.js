#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

(function() {
  'use strict';

  angular
    .module('${artifactId}')
    .config(config);

  /** @ngInject */
  function config(${symbol_dollar}logProvider, ${symbol_dollar}mdThemingProvider) {
    // Enable log
    ${symbol_dollar}logProvider.debugEnabled(true);

    ${symbol_dollar}mdThemingProvider.theme('default')
      .primaryPalette('teal')
      .accentPalette('deep-orange')
      .warnPalette('red');
  }

})();
