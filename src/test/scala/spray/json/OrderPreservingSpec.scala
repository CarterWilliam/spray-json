package spray.json

import com.typesafe.config.ConfigFactory
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class OrderPreservingSpec extends Specification {


  "JSON objects" should {

    "not preserve order by default" in new OrderPreservingScope{
      JsonParser(testJsonString).toString must not be equalTo (testJsonString).ignoreSpace
    }
    
    "preserve order if configuration flag is set" in new OrderPreservingScope {
      val configuration = ConfigFactory.parseString(
        """
          |spray-json.parsing.preserve-object-order = true
        """.stripMargin)

      JsonParser(testJsonString, configuration).toString must beEqualTo (testJsonString).ignoreSpace
    }
    
  }

}

trait OrderPreservingScope extends Scope {

  val testJsonString =
    """
      |{
      |  "name": "cosmos-cli",
      |  "version": "1.13.0",
      |  "description": "Command line interface for Cosmos",
      |  "main": "index.js",
      |  "directories": {
      |    "test": "test"
      |  },
      |  "bin": {
      |    "cosmos": "lib/cli.js"
      |  },
      |  "scripts": {
      |    "lint": "jshint .",
      |    "test": "mocha",
      |    "coverage": "istanbul cover _mocha -- -R dot"
      |  },
      |  "dependencies": {
      |    "async": "^0.9.0",
      |    "chalk": "^0.5.1",
      |    "columnify": "^1.2.1",
      |    "commander": "^2.5.0",
      |    "commander-tabtab": "~0.1.0",
      |    "inquirer": "^0.8.0",
      |    "lodash": "^3.0.0",
      |    "moment": "^2.8.4",
      |    "node-cosmos": "git+ssh://git@github.com:bbc/node-cosmos.git#v2.2.0",
      |    "octonode": "^0.7.6",
      |    "promptly": "^0.2.0",
      |    "shelljs": "^0.5.1"
      |  },
      |  "repository": {
      |    "type": "git",
      |    "url": "https://github.com/bbc/cosmos-cli.git"
      |  },
      |  "keywords": [
      |    "cosmos",
      |    "cli",
      |    "bbc"
      |  ],
      |  "author": "robin.murphy@bbc.co.uk",
      |  "license": "ISC",
      |  "bugs": {
      |    "url": "https://github.com/bbc/cosmos-cli/issues"
      |  },
      |  "homepage": "https://github.com/bbc/cosmos-cli",
      |  "private": true,
      |  "jshintConfig": {
      |    "quotmark": "single",
      |    "unused": true,
      |    "undef": true,
      |    "node": true,
      |    "globals": {
      |      "describe": false,
      |      "it": false,
      |      "before": false,
      |      "beforeEach": false,
      |      "after": false,
      |      "afterEach": false
      |    }
      |  },
      |  "devDependencies": {
      |    "istanbul": "^0.4.5",
      |    "jshint": "^2.5.10",
      |    "mocha": "^3.0.2",
      |    "sinon": "^1.17.5"
      |  }
      |}
    """.stripMargin

}
