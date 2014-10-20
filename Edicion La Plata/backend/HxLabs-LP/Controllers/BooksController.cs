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
using System.Net;


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
                books.Add(new Book(
                    "El Principito",
                    "Best-seller del escritor frances Antoine de Saint-Exupery. ",
                    "FABLE",
                    "Editorial Planeta"
                ));
                books.Add(new Book(
                   "El Hobbit",
                   "Novela fantastica de J. R. R. Tolkien.",
                   "FANTASY",
                   "Editorial Atlantida"
               ));
                books.Add(new Book(
                   "El Código de Da Vinci",
                   "Novela de misterio del escritor Dan Brown.",
                   "MISTERY",
                   "Editorial Estrada"
               ));
                PageLoad++;

            }
        }
        // Handle OPTIONS requests
        public HttpResponseMessage Options()
        {
            var response = new HttpResponseMessage();
            response.StatusCode = HttpStatusCode.OK;
            return response;
        }

        //
        // GET: /Books/
        public List<Book> GetAllBooks()
        {
            return books;
        }

        //
        // GET: /Books/Details/5
        public Book Get(int id)
        {
            Book book = books.FirstOrDefault((p) => p.id == id);
            return book;
        }

        //
        //POST: /Books/
        public HttpResponseMessage PostBook(Book book)
        {
            books.Add(book);
            var response = Request.CreateResponse<Book>(HttpStatusCode.Created, book);
            return response;

        }

        //
        //PUT: /Books/1
        public bool Put(int id, Book book)
        {
            book.id = id;
            if (book == null)
            {
                throw new ArgumentNullException("book");
            }
            int index = books.FindIndex(p => p.id == book.id);
            if (index == -1)
            {
                return false;
            }
            books.RemoveAt(index);
            books.Add(book);
            return true;
        }


    }
}
