using ApiHeroku.Data.Model;

namespace ApiHeroku.Data.Response
{
    public class BookingsByUserResponse
    {
        public IEnumerable<Booking>? BookingsByUser { get; set; }
        public string Message { get; set; }
        public int Code { get; set; }
    }
}
