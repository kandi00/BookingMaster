using BookingMaster.Data.Model;
using Microsoft.EntityFrameworkCore;
using System.Diagnostics.CodeAnalysis;

namespace BookingMaster.Data
{
    public class AppDbContext : DbContext
    {

        public AppDbContext([NotNullAttribute] DbContextOptions options) : base(options)
        {
        }

        public DbSet<Accommodation> Accomodations { get; set; } //Accomodation table

    }
}
