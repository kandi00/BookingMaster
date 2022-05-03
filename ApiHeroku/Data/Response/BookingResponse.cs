using ApiHeroku.Data.Model;

namespace ApiHeroku.Data.Response
{
    public class BookingResponse
    {
        public Booking Booking { get; set; }
        public string Message { get; set; }
        public int Code { get; set; }
    }
}
