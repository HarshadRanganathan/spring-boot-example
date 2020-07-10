package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/backend'
    }
    response {
        status OK()
        body (
            kind: $(anyNonBlankString())
        )
        headers {
            contentType('application/json')
        }
    }
}



