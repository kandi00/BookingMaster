using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace ApiHeroku.Data.Model
{
    public class Booking
    {
        [Required]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int ID { get; set; }

        [property: JsonConverter(typeof(DateOnlyConverter))]
        public DateOnly from_date { get; set; }

        [property: JsonConverter(typeof(DateOnlyConverter))]
        public DateOnly to_date { get; set; }

        [property: JsonConverter(typeof(DateOnlyConverter))]
        public DateOnly booking_date { get; set; }
        
        //many-to-many relationship
        public int RoomId { get; set; }
        public Room Room { get; set; }
        public int UserId { get; set; }
        public UserExample UserExample { get; set; }
    }
}
