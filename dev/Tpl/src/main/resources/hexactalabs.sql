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
-- Dumping data for table `books`
--

INSERT INTO `books` VALUES 	(1,'Best-seller del escritor frances Antoine de Saint-Exupery.',TRUE,'FABLE','Editorial Planeta','El principito',0), (2,'Novela de misterio del escritor Dan Brown.',TRUE,'MYSTERY','Editorial Estrada','El codigo Da Vinci',0), (3,'Novela fantastica de J. R. R. Tolkien.',TRUE,'FANTASY','Editorial Atlantida','El Hobbit',0), (4,'Novela de ciencia ficcion de Scott',TRUE,'CRIME','Editorial Pepin','Enders Game',0);

--
-- Dumping data for table `books_book_categories`
--
--
INSERT INTO `books_book_categories` VALUES (3,1),(1,2),(2,2),(4,2);

--
-- Dumping data for table `book_copies`
--

INSERT INTO `book_copies` VALUES (1,'Good','1','','Free',0,1),(2,'Good','2','','Loaned',0,1),(3,'Good','3','','Free',0,2),(4,'Good','4','','Free',0,3),(5,'Good','5','','Free',0,4);

--
-- Dumping data for table `books_book_copies`
--
INSERT INTO `books_book_copies` VALUES (1,1),(1,2),(2,3),(3,4),(4,5);

--
-- Dumping data for table `loans`
--

INSERT INTO `loans` VALUES (1,'2014-08-28 11:56:08','2014-08-29 11:56:08',0,1,1);
