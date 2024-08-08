const http = require('http')

server = http.createServer((req, res) => {
    url = req.url.slice(1)
    console.log(url)

    /// Comming Steps >> Choose a criteria for encrypting the token
})

server.listen(8080)