package com.example.applause.details

import com.example.applause.model.Location
import com.example.applause.model.Name
import com.example.applause.model.Picture
import com.example.applause.model.User
import spock.lang.Specification

class ContractDetailsPresenterSpec extends Specification {

    def user = buildUser()

    def presenter = new ContractDetailsPresenter(user)
    def view = Mock(ContantDetailsContract.View)

    def "should set header using first and last name"() {
        when:
        presenter.bind(view)

        then:
        1 * view.setHeader("Roy Solomon")
    }

    def "should set first name"() {
        when:
        presenter.bind(view)

        then:
        1 * view.setFirstName("Roy")
    }

    def "should set last name"() {
        when:
        presenter.bind(view)

        then:
        1 * view.setLastName("Solomon")
    }

    def "should load picture"() {
        when:
        presenter.bind(view)

        then:
        1 * view.loadPicture("someMediumUrl")
    }

    def "should set location street"() {
        when:
        presenter.bind(view)

        then:
        1 * view.setLocationStreet("Oxford Street")
    }

    def "should set location city"() {
        when:
        presenter.bind(view)

        then:
        1 * view.setLocationCity("London")
    }

    def "should set location state"() {
        when:
        presenter.bind(view)

        then:
        1 * view.setLocationState("United Kingdom")
    }

    def "should set location zip"() {
        when:
        presenter.bind(view)

        then:
        1 * view.setLocationZip("W1")
    }

    def "should set user e-mail"() {
        when:
        presenter.bind(view)

        then:
        1 * view.setEmail("roy@applause.com")
    }

    def "should set location map"() {
        when:
        presenter.bind(view)

        then:
        1 * view.setLocationMap("https://maps.google.com/?q=london,GB")
    }

    def "should set the easter egg"() {
        when:
        presenter.bind(view)

        then:
        1 * view.setEasterEgg(presenter)
    }

    //// Factory
    static def buildUser() {
        def picture = new Picture(medium: "someMediumUrl")
        def name = new Name(title: "Mr.", first: "roy", last: "solomon")
        def location = new Location(street: "oxford street", city: "london", state: "united Kingdom", zip: "w1")

        return new User(
                email: "roy@applause.com",
                picture: picture,
                name: name,
                location: location,
                nat: "GB"

        )
    }
}
