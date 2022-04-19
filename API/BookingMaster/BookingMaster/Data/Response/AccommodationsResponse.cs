using BookingMaster.Data.Model;

namespace BookingMaster.Data.Response
{
    public class AccommodationsResponse
    {
        public IEnumerable<Accommodation>? Accomodations { get; set; }
        public string Message { get; set; }
        public int Code { get; set; }
    }
}
