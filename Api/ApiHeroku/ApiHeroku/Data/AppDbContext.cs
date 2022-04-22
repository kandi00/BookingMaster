using ApiHeroku.Data.Model;
using Microsoft.EntityFrameworkCore;

namespace ApiHeroku.Data
{
    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options)
        {
        }


        public DbSet<ToDo> ToDos { get; set; }
    }
}
