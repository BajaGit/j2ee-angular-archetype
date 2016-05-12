#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

(function() {
  'use strict';

  angular
    .module('${artifactId}')
    .run(runBlock);

  /** @ngInject */
  function runBlock(${symbol_dollar}log) {

    ${symbol_dollar}log.debug('runBlock end');
  }

})();
