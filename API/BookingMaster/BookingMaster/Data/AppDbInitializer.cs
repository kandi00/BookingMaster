using BookingMaster.Data.Model;

namespace BookingMaster.Data
{
    public class AppDbInitializer
    {
        public static void Seed(IApplicationBuilder applicationBuilder)
        {
            using (var serviceScope = applicationBuilder.ApplicationServices.CreateScope())
            {
                var context = serviceScope.ServiceProvider.GetService<AppDbContext>();
                if (!context.Accomodations.Any())
                {
                    context.Accomodations.AddRangeAsync(
                        new Accommodation()
                        {
                            Name = "City Hotel Budapest",
                            Phone_number = "0345672812",
                            Type = Enums.AccomodationType.Hotel,
                            Location = "Budapest",
                            Total_Number_Of_Rooms = 5,
                            Number_Of_Free_Rooms = 5,
                            IsPublished = true
                        },
                        new Accommodation()
                        {
                            Name = "Five Season's Apartment",
                            Phone_number = "0256849326",
                            Type = Enums.AccomodationType.Apartment,
                            Location = "Budapest",
                            Total_Number_Of_Rooms = 3,
                            Number_Of_Free_Rooms = 3,
                            IsPublished = true
                        },
                        new Accommodation()
                        {
                            Name = "Hotel Lycium Debrecen",
                            Phone_number = "0215367284",
                            Type = Enums.AccomodationType.Hotel,
                            Location = "Debrecen",
                            Total_Number_Of_Rooms = 4,
                            Number_Of_Free_Rooms = 4,
                            IsPublished = true
                        });
                    context.SaveChanges();
                }
            }
        }

    }
}
