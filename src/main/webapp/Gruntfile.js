module.exports = function(grunt) {
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    browserify: {
      js: {
        src: 'app/js/mysmartfridge.js',
        dest: 'dist/js/mysmartfridge.js'
      }
    },
    sass: {
      dist: {
        files: [{
          expand: true,
          cwd: 'app/sass/',
          src: ['mysmartfridge.scss'],
          dest: 'dist/css',
          ext: '.css'
        }]
      }
    },
    copy : {
      // Workaround to be able to import css files with sass using sass import and not W3C import feature.
      cssAsScss: {
        expand: true,
        cwd: 'node_modules',
        src: ['**/*.css', '!**/*.min.css'],
        dest: 'node_modules',
        filter: 'isFile',
        ext: '.scss'
      },
      appCss: {
        expand: true,
        cwd: 'app/',
        src: ['**/*.html', '**/*.css'],
        dest: 'dist/',
      }
    }
  });

  grunt.loadNpmTasks('grunt-browserify');
  grunt.loadNpmTasks('grunt-contrib-sass');
  grunt.loadNpmTasks('grunt-contrib-copy');

  grunt.registerTask('build', ['copy:cssAsScss', 'browserify', 'sass', 'copy:appCss']);
}
