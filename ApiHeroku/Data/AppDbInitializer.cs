using ApiHeroku.Data.Model;

namespace ApiHeroku.Data
{
    public class AppDbInitializer
    {
        public static void Seed(IApplicationBuilder applicationBuilder)
        {
            using (var serviceScope = applicationBuilder.ApplicationServices.CreateScope())
            {
                var context = serviceScope.ServiceProvider.GetService<AppDbContext>();

                if (!context.UserInfosExample.Any())
                {
                    context.UserInfosExample.AddRangeAsync(
                        new UserExample() 
                        { 
                            
                            DisplayName = "Admin",
                            UserName = "admin",
                            Email = "admin@abc.com",
                            Password = "admin",
                            
                        }
                    );
                    context.SaveChanges();
                }

                if (!context.Bookings.Any())
                {
                    context.Bookings.AddRangeAsync(
                        new Booking()
                        {
                            from_date = DateTime.Now,
                            to_date = DateTime.Now.AddDays(4),
                            RoomId = 1,
                            UserId = 1
                        }
                    );
                    context.SaveChanges();
                }

                if (!context.Accomodations.Any())
                {
                    context.Accomodations.AddRangeAsync(
                        new Accommodation()
                        {
                            Name = "City Hotel Budapest",
                            Phone_number = "0345672812",
                            Type = Enums.AccomodationType.Hotel,
                            Location = "Budapest",
                            Total_Number_Of_Rooms = 3,
                            IsPublished = true
                        },
                        new Accommodation()
                        {
                            Name = "Five Season's Apartment",
                            Phone_number = "0256849326",
                            Type = Enums.AccomodationType.Apartment,
                            Location = "Budapest",
                            Total_Number_Of_Rooms = 2,
                            IsPublished = true
                        },
                        new Accommodation()
                        {
                            Name = "Hotel Lycium Debrecen",
                            Phone_number = "0215367284",
                            Type = Enums.AccomodationType.Hotel,
                            Location = "Debrecen",
                            Total_Number_Of_Rooms = 2,
                            IsPublished = true
                        });
                    context.SaveChanges();
                }

                if (!context.Rooms.Any())
                {
                    context.Rooms.AddRangeAsync(
                        new Room()
                        {
                            Price = 80,
                            Currency = "EURO",
                            Capacity = 2,
                            AccommodationId = 1
                        },
                       new Room()
                       {
                           Price = 120,
                           Currency = "EURO",
                           Capacity = 3,
                           AccommodationId = 1
                       },
                       new Room()
                       {
                           Price = 100,
                           Currency = "EURO",
                           Capacity = 2,
                           AccommodationId = 1
                       },
                       new Room()
                       {
                           Price = 100,
                           Currency = "EURO",
                           Capacity = 2,
                           AccommodationId = 2
                       },
                       new Room()
                       {
                           Price = 100,
                           Currency = "EURO",
                           Capacity = 2,
                           AccommodationId = 2
                       },
                       new Room()
                       {
                           Price = 200,
                           Currency = "EURO",
                           Capacity = 4,
                           AccommodationId = 3
                       },
                       new Room()
                       {
                           Price = 100,
                           Currency = "EURO",
                           Capacity = 2,
                           AccommodationId = 3
                       });
                    context.SaveChanges();
                }
            }
        }
    }
}
