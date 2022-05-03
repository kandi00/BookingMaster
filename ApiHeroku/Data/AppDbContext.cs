using ApiHeroku.Data.Model;
using Microsoft.EntityFrameworkCore;

namespace ApiHeroku.Data
{
    public partial class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options)
        {
        }

        //for the auth example
        public virtual DbSet<UserExample>? UserInfosExample { get; set; }
        //for the auth example

       

        public DbSet<ToDo> ToDos { get; set; }

        public DbSet<Accommodation> Accomodations { get; set; } //Accomodation table
        public DbSet<Room> Rooms { get; set; } //Room table

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            //for authentication

            /*modelBuilder.Entity<UserExample>(entity =>
            {

                entity.ToTable("UserExample");

                entity.Property(e => e.UserId).HasColumnName("UserId");
                entity.Property(e => e.DisplayName).HasMaxLength(60).IsUnicode(false);
                entity.Property(e => e.UserName).HasMaxLength(30).IsUnicode(false);
                entity.Property(e => e.Email).HasMaxLength(50).IsUnicode(false);
                entity.Property(e => e.Password).HasMaxLength(20).IsUnicode(false);
                entity.Property(e => e.CreatedDate).IsUnicode(false);
            });*/
            modelBuilder.Entity<UserExample>()
                .HasKey(e => e.ID);
            //for authentication



            // configures one-to-many relationship
            modelBuilder.Entity<Room>()
                .HasOne(a => a.Accommodation)
                .WithMany(r => r.Rooms)
                .HasForeignKey(ai => ai.AccommodationId);

            //for authentication
            OnModelCreatingPartial(modelBuilder);
        }

        //for authentication
        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
