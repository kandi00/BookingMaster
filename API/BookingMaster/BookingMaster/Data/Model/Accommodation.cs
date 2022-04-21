using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using static BookingMaster.Data.Model.Enums;

namespace BookingMaster.Data.Model
{
    public class Accommodation
    {
        [Required]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int ID { get; set; }
        public string Name { get; set; }
        public string Phone_number { get; set; }
        public AccomodationType Type { get; set; }
        public string Location { get; set; }
        public int Total_Number_Of_Rooms { get; set; }
        public int Number_Of_Free_Rooms { get; set; }
        public bool IsPublished { get; set; }

        public ICollection<Room> Rooms { get; set; }
    }
}
