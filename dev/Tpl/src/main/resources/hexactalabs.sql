USE `hexactalabs`;

--
-- Dumping data for table `users`
--

INSERT INTO `users` VALUES (1,'admin@hexacta.com','','admin','admin',0),(2,'emalvino@hexacta.com','\0','malvino','edu',0);

--
-- Dumping data for table `book_categories`
--

INSERT INTO `book_categories` VALUES (1,'Libro en formato electronico','ebook',0),(2,'Libro en formato fisico','fisico',0);

--
-- Dumping data for table `book_copies`
--

INSERT INTO `book_copies` VALUES (1,'Good','1','','Free',0,NULL),(2,'Good','2','','Loaned',0,NULL),(3,'Good','3','','Free',0,NULL),(4,'Good','4','','Free',0,NULL),(5,'Good','5','','Free',0,NULL);

--
-- Dumping data for table `books`
--

INSERT INTO `books` VALUES (1,'EEUU','Best-seller del escritor frances Antoine de Saint-Exupery.','','978-0-152-16415-7','El principito','Editorial Planeta',0),(2,'EEUU','Novela de misterio del escritor Dan Brown.','','84-95618-60-5','El codigo Da Vinci','Editorial Estrada',0),(3,'United Kingdom','Novela fantastica de J. R. R. Tolkien.','','84-450-7037-1','El Hobbit','Editorial Atlantida',0),(4,'defaultCountry','Novela de ciencia ficcion de Scott','','defaultISBN','Enders Game','Editorial pepin',0);

--
-- Dumping data for table `books_book_categories`
--
--
--INSERT INTO `books_book_categories` VALUES (3,1),(1,2),(2,2),(4,2);

--
-- Dumping data for table `books_book_copies`
--

--INSERT INTO `books_book_copies` VALUES (1,1),(2,3),(3,4),(4,5);

--
-- Dumping data for table `loans`
--

INSERT INTO `loans` VALUES (1,'2014-08-28 11:56:08','2014-08-28 11:56:08',0,1,1);
