using System.Text.Json.Serialization;

namespace ApiHeroku.Data.ViewModel
{
    public class BookingViewModel
    {
        [property: JsonConverter(typeof(DateOnlyConverter))]
        public DateOnly from_date { get; set; }

        [property: JsonConverter(typeof(DateOnlyConverter))]
        public DateOnly to_date { get; set; }
        public int RoomId { get; set; }
        public int UserId { get; set; }
    }
}
