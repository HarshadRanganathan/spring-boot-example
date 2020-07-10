package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/person'
    }
    response {
        status OK()
        body([
                firstName: "Harshad",
                lastName: "Ranganathan"
        ])
        headers {
            contentType('application/json')
        }
    }
}



