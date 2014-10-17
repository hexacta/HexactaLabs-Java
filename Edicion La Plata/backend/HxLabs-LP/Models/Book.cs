using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HxLabs_LP.Models
{
    public class Book
    {
        public int id { get; set; }
        public string title { get; set; }
        public string description { get; set; }
        public string genre { get; set; }
        public string publisher { get; set; }
    }
}