#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var ${symbol_dollar}{symbol_dollar} = require('gulp-load-plugins')({
  pattern: ['gulp-*', 'main-bower-files', 'uglify-save-license', 'del']
});

gulp.task('partials', function () {
  return gulp.src([
    path.join(conf.paths.src, '/app/**/*.html'),
    path.join(conf.paths.tmp, '/serve/app/**/*.html')
  ])
    .pipe(${symbol_dollar}{symbol_dollar}.htmlmin({
      removeEmptyAttributes: true,
      removeAttributeQuotes: true,
      collapseBooleanAttributes: true,
      collapseWhitespace: true
    }))
    .pipe(${symbol_dollar}{symbol_dollar}.angularTemplatecache('templateCacheHtml.js', {
      module: 'sbamaterial',
      root: 'app'
    }))
    .pipe(gulp.dest(conf.paths.tmp + '/partials/'));
});

gulp.task('html', ['inject', 'partials'], function () {
  var partialsInjectFile = gulp.src(path.join(conf.paths.tmp, '/partials/templateCacheHtml.js'), { read: false });
  var partialsInjectOptions = {
    starttag: '<!-- inject:partials -->',
    ignorePath: path.join(conf.paths.tmp, '/partials'),
    addRootSlash: false
  };

  var htmlFilter = ${symbol_dollar}{symbol_dollar}.filter('*.html', { restore: true });
  var jsFilter = ${symbol_dollar}{symbol_dollar}.filter('**/*.js', { restore: true });
  var cssFilter = ${symbol_dollar}{symbol_dollar}.filter('**/*.css', { restore: true });

  return gulp.src(path.join(conf.paths.tmp, '/serve/*.html'))
    .pipe(${symbol_dollar}{symbol_dollar}.inject(partialsInjectFile, partialsInjectOptions))
    .pipe(${symbol_dollar}{symbol_dollar}.useref())
    .pipe(jsFilter)
    .pipe(${symbol_dollar}{symbol_dollar}.sourcemaps.init())
    .pipe(${symbol_dollar}{symbol_dollar}.ngAnnotate())
    .pipe(${symbol_dollar}{symbol_dollar}.uglify({ preserveComments: ${symbol_dollar}{symbol_dollar}.uglifySaveLicense })).on('error', conf.errorHandler('Uglify'))
    .pipe(${symbol_dollar}{symbol_dollar}.rev())
    .pipe(${symbol_dollar}{symbol_dollar}.sourcemaps.write('maps'))
    .pipe(jsFilter.restore)
    .pipe(cssFilter)
    // .pipe(${symbol_dollar}.sourcemaps.init())
    .pipe(${symbol_dollar}.replace('../../bower_components/material-design-iconfont/iconfont/', '../fonts/'))
    .pipe(${symbol_dollar}.cssnano())
    .pipe(${symbol_dollar}.rev())
    // .pipe(${symbol_dollar}.sourcemaps.write('maps'))
    .pipe(cssFilter.restore)
    .pipe(${symbol_dollar}.revReplace())
    .pipe(htmlFilter)
    .pipe(${symbol_dollar}.htmlmin({
      removeEmptyAttributes: true,
      removeAttributeQuotes: true,
      collapseBooleanAttributes: true,
      collapseWhitespace: true
    }))
    .pipe(htmlFilter.restore)
    .pipe(gulp.dest(path.join(conf.paths.dist, '/')))
    .pipe(${symbol_dollar}.size({ title: path.join(conf.paths.dist, '/'), showFiles: true }));
  });

// Only applies for fonts from bower dependencies
// Custom fonts are handled by the "other" task
gulp.task('fonts', function () {
  return gulp.src(${symbol_dollar}.mainBowerFiles().concat('bower_components/material-design-iconfont/iconfont/*'))
    .pipe(${symbol_dollar}.filter('**/*.{eot,otf,svg,ttf,woff,woff2}'))
    .pipe(${symbol_dollar}.flatten())
    .pipe(gulp.dest(path.join(conf.paths.dist, '/fonts/')));
});

gulp.task('other', function () {
  var fileFilter = ${symbol_dollar}.filter(function (file) {
    return file.stat.isFile();
  });

  return gulp.src([
    path.join(conf.paths.src, '/**/*'),
    path.join('!' + conf.paths.src, '/**/*.{html,css,js}')
  ])
    .pipe(fileFilter)
    .pipe(gulp.dest(path.join(conf.paths.dist, '/')));
});

gulp.task('clean', function () {
  return ${symbol_dollar}.del([path.join(conf.paths.dist, '/'), path.join(conf.paths.tmp, '/')]);
});

gulp.task('build', ['html', 'fonts', 'other']);
