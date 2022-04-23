using ApiHeroku.Data.Model;

namespace ApiHeroku.Data.Response
{
    public class AccommodationsResponse
    {
        public IEnumerable<Accommodation>? Accomodations { get; set; }
        public string Message { get; set; }
        public int Code { get; set; }
    }
}
