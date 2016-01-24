module.exports = function(grunt) {
  var app_src_root="app/";
  var app_dist_root="../resources/public/"

  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    browserify: {
      js: {
        src: app_src_root+'js/mysmartfridge.js',
        dest: app_dist_root+'js/mysmartfridge.js'
      }
    },
    sass: {
      dist: {
        files: [{
          expand: true,
          cwd: app_src_root+'sass/',
          src: ['mysmartfridge.scss'],
          dest: app_dist_root+'css/',
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
      appHtml: {
        expand: true,
        cwd: app_src_root,
        src: ['**/*.html'],
        dest: app_dist_root,
      }
    },
    defaultWatch: {
      js: {
        files: [app_src_root+'**/*.js'],
        tasks: ['browserify']
      },
      sass: {
        files: [app_src_root+'**/*.scss',],
        tasks: ['sass']
      },
      html: {
        files: [app_src_root+'**/*.html'],
        tasks: ['copy:appHtml']
      },
    },
    clean: {
      app: [app_dist_root],
      options: {force: true}
    }
  });

  grunt.loadNpmTasks('grunt-browserify');
  grunt.loadNpmTasks('grunt-contrib-sass');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-clean');

  grunt.registerTask('build', ['copy:cssAsScss', 'browserify', 'sass', 'copy:appHtml']);

  grunt.renameTask('watch', 'defaultWatch');
  grunt.registerTask('watch', ['build', 'defaultWatch']);
}
