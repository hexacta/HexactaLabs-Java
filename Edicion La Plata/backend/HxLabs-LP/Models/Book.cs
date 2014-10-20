using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HxLabs_LP.Models
{
   
    public class Book
    {
        private static int ID = 0;
        public int id;
        public string title;
        public string description;
        public string genre; 
        public string publisher;

        public Book(string title, string description, string genre, string publisher)
        {
            this.title = title;
            this.description = description;
            this.genre = genre;
            this.publisher = publisher;
            this.id = System.Threading.Interlocked.Increment(ref ID);
            
        }
      

    }
}