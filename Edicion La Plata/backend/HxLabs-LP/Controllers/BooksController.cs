using HxLabs_LP.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
using System.Web.Mvc;
using HxLabs_LP.Controllers;
using System.Net.Http;
using Newtonsoft.Json.Linq;


namespace HxLabs_LP.Controllers
{
    public class BooksController : ApiController
    {
        public static List<Book> books = new List<Book>();
        public static int PageLoad = 1;

        public BooksController()
        {
            if (PageLoad == 1)
            {
                books.Add(new Book
                {
                    id = 1,
                    title = "El Principito",
                    description = "Best-seller del escritor frances Antoine de Saint-Exupery. ",
                    genre = "Fabula",
                    publisher = "Editorial Planeta"
                });
                books.Add(new Book
                {
                    id = 2,
                    title = "El Hobbit",
                    description = "Novela fantastica de J. R. R. Tolkien. ",
                    genre = "Fantasía",
                    publisher = "Editorial Atlantida"
                });
                books.Add(new Book
                {
                    id = 3,
                    title = "El Código de Da Vinci",
                    description = "Novela de misterio del escritor Dan Brown. ",
                    genre = "Misterio",
                    publisher = "Editorial Estrada"
                });
                PageLoad++;
            }
        }

        //
        // GET: /Books/
        public List<Book> GetAllBooks()
        {
            return books;
        }

        //
        // GET: /Books/Details/5
        public IHttpActionResult Details(int id)
        {
            Book book = books.FirstOrDefault((p) => p.id == id);
            if (book == null)
            {
                return NotFound();
            }
            
            return Ok(book);
        }
    }
}
