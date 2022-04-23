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

        public DbSet<Accommodation> Accomodations { get; set; } //Accomodation table
        public DbSet<Room> Rooms { get; set; } //Room table

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // configures one-to-many relationship
            modelBuilder.Entity<Room>()
                .HasOne(a => a.Accommodation)
                .WithMany(r => r.Rooms)
                .HasForeignKey(ai => ai.AccommodationId);
        }
    }
}
