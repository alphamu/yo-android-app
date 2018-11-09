var Generator = require('yeoman-generator');
const glob = require('glob');
var path = require('path');

module.exports = class extends Generator {
  // The name `constructor` is important here
  constructor(args, opts) {
    // Calling the super constructor is important so our generator is correctly set up
    super(args, opts);

    
  }
  async prompting() {
    this.answers = await this.prompt([{
      type    : 'input',
      name    : 'name',
      message : 'Name of your project',
      default : 'My App'
    },{
      type    : 'input',
      name    : 'package',
      message : 'Package name for your project',
      default : 'com.example.app'
    }, {
      type    : 'input',
      name    : 'lang',
      message : 'Java or Kotlin? (j/k)',
      default : 'java'
    }]);

    this.log('app name: ', this.answers.name);
    this.log('app package: ', this.answers.package);
    this.log('app language: ', this.answers.lang);
    var firstChar = this.answers.lang.trim().charAt(0)
    if (firstChar === 'k' || firstChar === 'K') {
      this._exportKotlinApp()
    } else  if (firstChar === 'j' || firstChar === 'J') {
      this._exportJavaApp()
    } else {
      this.console.error('Invalid language selected.')
    }
  }

  _exportJavaApp() {
    this._exportCode(this.templatePath('java'))
  }

  _exportKotlinApp() {
    this._exportCode(this.templatePath('kotlin'))
  }

  _exportCode(tp) {
    console.log(this.templatePath())
    console.log(this.destinationRoot())
    const destDir = './' + this.answers.name.replace(/\W/gi, '')
    // Copy everything as it is
    this.fs.copyTpl(tp, destDir, {
      name: this.answers.name,
      package: this.answers.package
    })
    //Create package directories
    var dirs = destDir + '/app/src/main/java/' + this.answers.package.replaceAll('.', '/')
    console.log(dirs)
    this.fs.copyTpl(tp+'/app/src/main/java/com/alimuzaffar/blank/', dirs, {
      name: this.answers.name,
      package: this.answers.package
    });
    this.fs.delete(destDir + '/app/src/main/java/com/alimuzaffar/blank/')
  }
};

String.prototype.replaceAll = function(search, replacement) {
  var target = this;
  return target.split(search).join(replacement);
};
