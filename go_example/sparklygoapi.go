package main

import (
    "fmt"
    "log"
    "net/http"
    "os"
)

//Declaration of package variables
var (
    err      error
    driver   neo4j.Driver
    session  neo4j.Session
    result   neo4j.Result
    movie interface{}
)

//This is the equivalent to the "controller" in the movie design. But here we didn't do a big deal of an abstraction because we do not know how to do it here, in golang.
func movieHandler(res http.ResponseWriter, req *http.Request) {

    var newMovieName string

    //Connects to a ficticious Neo4J server, which the graph database already created and the due credentials.
    driver, session, err := neo4jConnect("bolt://localhost","neo4j","neo4j")
    //performs the transaction...
    newMovieName,err := addNewMovie(req.URL.Path[1:])

    if err!=nil {
        fmt.Println(err)
        fmt.Fprintf(res, "An error occured, the movie %s wasn't added", req.URL.Path[1:]) //here responds to the caller
    } else {
        fmt.Fprintf(res, "Added the movie %s!", req.URL.Path[1:]) //here responds to the caller
        //We use this as a means to log.
        fmt.Println("RESTfulServ. on:8093, Controller, completed to add the Movie:", req.URL.Path[1:])
    }

}

//This is the main method, equivalent to java's "static void main()" or to its C homonymous.
func main() {

    //This is the list of end points to connect to the rest service.
    http.HandleFunc("/addNewMovie", movieHandler) //here we declare what we do with every hit to our REST API

    fmt.Println("Starting Restful services...")
    fmt.Println("Using port:8093")

    //Starts the standalone server to listen in this port 8093; TODO expose these ports on the docker image.
    err := http.ListenAndServe(":8093", nil)

    log.Print(err)
    errorHandler(err)
}

//Just the equivalent to the "catch" section in a regular java Try-catch block.
func errorHandler(err error){

    if err!=nil {
        fmt.Println(err)
        os.Exit(1)
    }

}

// Here we start the database connection...
func neo4jConnect(uri, username, password string)(driver, session, error){

    //We start the driver here, we need to pass it the neo4j url, usernama and password to connect with.
    driver, err = neo4j.NewDriver(uri, neo4j.BasicAuth(username, password, ""))

    //Any error handler
    if err != nil {
        return nil,nil, err
    }
    defer driver.Close() //<-- This is amazing GO lang coding! it just leaves this command to run when the present function ends!

    //Connection start up...
    session, err = driver.Session(neo4j.AccessModeWrite)
    if err != nil {
        return nil, nil, err
    }
    defer session.Close()

    return driver, session, nil
}

//This is the function that will interact with Neo4J - Here occurs the transaction.
func addNewMovie(newName string) (string, error) {

    //Here we perform the proper transaction execution.
    movie, err = session.WriteTransaction(func(transaction neo4j.Transaction) (interface{}, error) {

        result, err = transaction.Run(
            "CREATE (a:Movie) SET a.name = $name RETURN a.name + ', from node ' + id(a)",
            map[string]interface{}{"name": newName}) //cypher create command here...

        //Error handling here...
        if err != nil {
            return nil, err
        }

        //Consuming the return (we putter that in the create as well, just like a message)
        if result.Next() {
            return result.Record().GetByIndex(0), nil
        }

        return nil, result.Err()
    }) // transaction ends here...

    if err != nil {
        return "", err
    }

    return movie.(string), nil
}
