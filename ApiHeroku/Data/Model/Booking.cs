using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ApiHeroku.Data.Model
{
    public class Booking
    {
        [Required]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int ID { get; set; }
        public DateTime from_date { get; set; }
        public DateTime to_date { get; set; }
        public DateTime  booking_date { get; set; }

        //many-to-many relationship
        public int RoomId { get; set; }
        public Room Room { get; set; }
        public int UserId { get; set; }
        public UserExample UserExample { get; set; }
    }
}
