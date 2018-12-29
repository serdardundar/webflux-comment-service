# webflux-comment-service

This project produces anonymous social media comments using webflux spring reactor api. 

Comment model is created to handle comment objects. There is no repository in this project but service. This "Service" class handles producing anonymous comments using Flux.

Finally a rest controller is implemented for getting these comments as a stream. 

Some test functions are developed to test creating anonymous comments.

This project must be only used to get anonymous data. Another project will be developed and placed in this Github account that gets those anonymous data and puts it Redis and Mongo DB. That project is [reactive-comment-data-service](https://github.com/serdardundar/reactive-comment-data-service). 

