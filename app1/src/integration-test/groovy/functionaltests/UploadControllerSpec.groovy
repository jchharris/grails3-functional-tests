package functionaltests

import grails.test.mixin.integration.Integration
import grails.transaction.Transactional

import spock.lang.*
import geb.spock.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@Integration(applicationClass=functionaltests.Application)
class UploadControllerSpec extends GebSpec {


    void "Test file upload"() {
        when:"When go to an upload page"
            go "/upload/index"

            def f = File.createTempFile("uploadtest", "txt")
            f.deleteOnExit()
            f.text = "Test upload"
            def form = $('#myForm')

            form.myFile = f.absolutePath
            $('#input1').click()

        then:"The file is uploaded"
            $('p').text() == 'Test upload'

    }

    void "Test file upload parameters"() {
        when:"When go to an upload page"
        go "/upload/index"

        def f = File.createTempFile("uploadtest", "txt")
        f.deleteOnExit()
        f.text = "Test upload"
        def form = $('#myForm2')

        form.myFile = f.absolutePath
        $('#input2').click()

        then:"The file is uploaded"
        $('p').text() == 'ok'

    }
}
