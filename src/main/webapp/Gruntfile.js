module.exports = function(grunt) {

  grunt.initConfig({
    // Read package config from npm module
    pkg: grunt.file.readJSON('package.json'),

    // Javascript compilation. The main javascript file is mysmartfrifge.js, and is compiled
    // into mysmartfridge.dist.js. This is the only file that has to be included in the index.html.
    browserify: {
      js: {
        src: 'js/mysmartfridge.js',
        dest: 'js/mysmartfridge.dist.js'
      },
      options: {
        browserifyOptions: {
          debug: true
        }
      }
    },

    // Sass compilation. Files in the sass/ directory are compiled and put into the css/ directory.
    sass: {
      dist: {
        files: [{
          expand: true,
          cwd: 'sass/',
          src: ['mysmartfridge.scss'],
          dest: 'css/',
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
    },

    // Settings for watch task. It is separated in several parts to avoid a complete recompilation each time a file changes.
    watch: {
      js: {
        files: ['**/*.js', '!**/*.dist.js'],
        tasks: ['browserify']
      },
      sass: {
        files: ['**/*.scss'],
        tasks: ['sass']
      }
    },

    // Clean task. Remove every generated file.
    clean: {
      app: ['css/', 'js/mysmartfridge.dist.js']
    }
  });

  grunt.loadNpmTasks('grunt-browserify');
  grunt.loadNpmTasks('grunt-contrib-sass');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-clean');

  grunt.registerTask('build', ['copy:cssAsScss', 'browserify', 'sass']);

  grunt.registerTask('build-watch', ['build', 'watch']);
}
